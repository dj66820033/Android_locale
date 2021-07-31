package com.dry.locale.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dry.locale.R;
import com.dry.locale.adapters.AllAdapter;
import com.dry.locale.beans.ItemBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

/**
 * A fragment representing a list of Items.
 */
public class AllFragment extends Fragment {

    RecyclerView all_recycler;
    private SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public AllFragment() {
    }

    public static AllFragment newInstance() {
        AllFragment fragment = new AllFragment();
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
        View view = inflater.inflate(R.layout.main_fragment, container, false);
        all_recycler = view.findViewById(R.id.all_recycler);
        sharedPreferences = getContext().getSharedPreferences("my_country", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        getAll();
        return view;
    }

    private void getAll(){
        List<ItemBean> listdata = new ArrayList<>();
        String[] locales = Locale.getISOCountries();
        Locale[] countries = Locale.getAvailableLocales();
        Map<String, String> languagesMap = new TreeMap<String, String>();
        String txt = "";

        for (Locale obj : countries){
            if((obj.getDisplayCountry()!=null)&&(!obj.getDisplayCountry().equals(""))){
                languagesMap.put(obj.getCountry(),obj.getLanguage());
            }
        }
        for (String countryCode : locales){
            ItemBean data = new ItemBean();
            Locale obj = null;
            if(languagesMap.get(countryCode)==null){
                obj = new Locale("", countryCode);
            }else{
                obj = new Locale(languagesMap.get(countryCode), countryCode);
            }
            txt = obj.getDisplayCountry()+"("+obj.getDisplayCountry(Locale.CHINESE)+"): "+obj.getDisplayLanguage();
            data.country = txt;
            listdata.add(data);
        }
        AllAdapter allAdapter = new AllAdapter(listdata, getContext(),editor);
        all_recycler.setAdapter(allAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        all_recycler.setLayoutManager(linearLayoutManager);
        all_recycler.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));

    }
}