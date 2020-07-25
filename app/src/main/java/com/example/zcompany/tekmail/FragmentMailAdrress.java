package com.example.zcompany.tekmail;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class FragmentMailAdrress extends Fragment {
    private View view;
    private Button buttonCreate;
    private TabLayout tabs;
    private ViewPager viewpager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mail_adrress,container,false);

        buttonCreate=view.findViewById(R.id.buttonCreate);



       //init();


    return view;}

    void init()
    {
        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("tıklanma calisti","mail adresi olusturuldu");
                FragmentMailBox fragment =new FragmentMailBox();
                startActivity(new Intent(getContext(),fragment.getClass()));


            }
        });




    }




}
