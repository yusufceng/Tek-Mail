package com.example.zcompany.tekmail;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.LinearLayout;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity implements FragmentMailAdrress.SendMessage {
    private TabLayout tabs;
    private LinearLayout LinearLayoutNew;
    private ViewPager viewpager;
    ProgressDialog cancelDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        tabs = findViewById(R.id.tabs);
        viewpager = findViewById(R.id.viewpager);


        init();
    }

    void init() {

        MyViewPagerAdapter adapter = new MyViewPagerAdapter(getSupportFragmentManager());
        viewpager.setAdapter(adapter);
        viewpager.setAdapter(adapter);
        tabs.setupWithViewPager(viewpager);

        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }

    @Override
    public void sendData(String message) {
        String tag = "android:switcher:" + R.id.viewpager + ":" + 1;
        FragmentMailBox f = (FragmentMailBox) getSupportFragmentManager().findFragmentByTag(tag);
        f.displayReceivedData(message);
        tabs.getTabAt(1).select();
    }

    public void checkConnection() {

        ConnectivityManager cm = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        if (null == activeNetwork) {
            //Toast.makeText(this, "İnternete bağlı değilsiniz", Toast.LENGTH_SHORT).show();
            createCancelProgressDialog("İnternet bağlantısı yok", "Lütfen internet bağlantınızı kontrol ediniz..!", "Tamam");
        }


    }

    private void createCancelProgressDialog(String title, String message, String buttonText) {
        cancelDialog = new ProgressDialog(this);
        cancelDialog.setTitle(title);
        cancelDialog.setMessage(message);
        cancelDialog.setButton(buttonText, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(getApplicationContext(), OfflineActivity.class));
                finish();
            }
        });
        cancelDialog.show();
    }

    public void onBackPressed() {
        if (tabs.getSelectedTabPosition() == 1) {
            tabs.getTabAt(0).select();

        } else
            finish();
    }


}


