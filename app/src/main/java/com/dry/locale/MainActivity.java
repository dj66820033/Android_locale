package com.dry.locale;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.dry.locale.fragment.AllFragment;
import com.dry.locale.fragment.PreferredFragment;

public class MainActivity extends AppCompatActivity {
    Button button_all,button_Preferred;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }

        button_all = findViewById(R.id.button_all);
        button_Preferred = findViewById(R.id.button_Preferred);
        button_all.setEnabled(false);

        AllFragment all = new AllFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame,all)
                .commit();
    }

    public void all_clicked(View view){
        button_all.setEnabled(false);
        button_Preferred.setEnabled(true);
        AllFragment all = new AllFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame,all)
                .commit();
    }

    public void Preferred_clicked(View view){
        button_Preferred.setEnabled(false);
        button_all.setEnabled(true);
        PreferredFragment preferred = new PreferredFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame,preferred)
                .commit();
    }


}