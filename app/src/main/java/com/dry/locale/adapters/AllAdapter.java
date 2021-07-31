package com.dry.locale.adapters;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.dry.locale.R;
import com.dry.locale.beans.ItemBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class AllAdapter extends RecyclerView.Adapter<AllAdapter.ViewHolder> {

    private final List<ItemBean> listdata;
    private final Context context;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public AllAdapter(List<ItemBean> listdata, Context context,SharedPreferences.Editor editor){
        this.listdata = listdata;
        this.context = context;
        this.editor = editor;
    }

    @Override
    public AllAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_listview,parent,false);

        return new ViewHolder(view);
    }

    int num = 0;
    List<ItemBean> olddata;
    @Override
    public void onBindViewHolder(@NonNull AllAdapter.ViewHolder holder, int position) {
        sharedPreferences = context.getSharedPreferences("my_country",Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        Gson gson =new Gson();
        String getCountries = sharedPreferences.getString("countries",null);
        if (getCountries != null){
            olddata = gson.fromJson(getCountries,new TypeToken<List<ItemBean>>(){}.getType());
            num = olddata.size();
        }

        List<ItemBean> newdata = new ArrayList<>();
        Boolean[] check = {false};
        holder.setData(listdata.get(position));
        holder.txt_country.setOnClickListener(new TextView.OnClickListener() {
            @Override
            public void onClick(View v) {
                newdata.add(listdata.get(position));
                if (num>0 && num<5){
                    for(int i=0;i<num;i++){
                        if(listdata.get(position).country.equals(olddata.get(i).country)){
                            check[0] = true;
                        }
                        newdata.add(olddata.get(i));
                    }
                }else if(num>=5){
                    for(int i=0;i<4;i++){
                        if(listdata.get(position).country.equals(olddata.get(i).country)){
                            check[0] = true;
                        }
                        newdata.add(olddata.get(i));
                    }
                }

                if(check[0] == false){
                    Toast.makeText(context, "add success", Toast.LENGTH_SHORT).show();
                    String newjson = gson.toJson(newdata);
                    editor.putString("countries",newjson);
                    editor.commit();
                }else{
                    Toast.makeText(context, "already added", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (listdata != null){
            return listdata.size();
        }
        else return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txt_country;

        public ViewHolder(View view) {
            super(view);
            txt_country = view.findViewById(R.id.txt_country);
        }

        public void setData(ItemBean itemBean){
            txt_country.setText(itemBean.country);
        }
    }
}