package com.dry.locale.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dry.locale.R;
import com.dry.locale.beans.ItemBean;

import java.util.List;

public class PreferredAdapter extends RecyclerView.Adapter<PreferredAdapter.ViewHolder> {

    private final List<ItemBean> listdata;

    public PreferredAdapter(List<ItemBean> listdata){
        this.listdata = listdata;
    }

    @Override
    public PreferredAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_listview,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PreferredAdapter.ViewHolder holder, int position) {
        holder.setData(listdata.get(position));
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