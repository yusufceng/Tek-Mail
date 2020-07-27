package com.example.zcompany.tekmail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    private TabLayout tabs;
    private ViewPager viewpager;
    private Button buttonCreate;
    public String alias_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    void init() {
        tabs = findViewById(R.id.tabs);
        viewpager = findViewById(R.id.viewpager);
        buttonCreate = findViewById(R.id.buttonCreate);

        MyViewPagerAdapter adapter = new MyViewPagerAdapter(getSupportFragmentManager());
        adapter.fragmentEkle(new FragmentMailAdrress(), "Home");
        adapter.fragmentEkle(new FragmentMailBox(), "Mail Box");

        viewpager.setAdapter(adapter);
        tabs.setupWithViewPager(viewpager);




    }

}
