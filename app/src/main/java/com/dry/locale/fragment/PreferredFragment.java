package com.dry.locale.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dry.locale.R;
import com.dry.locale.adapters.PreferredAdapter;
import com.dry.locale.beans.ItemBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class PreferredFragment extends Fragment {
    RecyclerView preferred_recycler;
    private SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public PreferredFragment() {
    }

    public static PreferredFragment newInstance() {
        PreferredFragment fragment = new PreferredFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.second_fragment, container, false);
        preferred_recycler = view.findViewById(R.id.preferred_recycler);
        sharedPreferences = getContext().getSharedPreferences("my_country", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        getPreferred();
        return view;
    }

    private void getPreferred(){
        Gson gson =new Gson();
        String getCountries = sharedPreferences.getString("countries",null);
        List<ItemBean> olddata;
        olddata = gson.fromJson(getCountries,new TypeToken<List<ItemBean>>(){}.getType());

        PreferredAdapter preferredAdapter = new PreferredAdapter(olddata);
        preferred_recycler.setAdapter(preferredAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        preferred_recycler.setLayoutManager(linearLayoutManager);
        preferred_recycler.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));

    }
}
