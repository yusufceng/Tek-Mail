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
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements FragmentMailAdrress.SendMessage {
    private TabLayout tabs;
    private ViewPager viewpager;
    ProgressDialog cancelDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
<<<<<<< HEAD
        tabs = findViewById(R.id.tabs);
        viewpager = findViewById(R.id.viewpager);
=======

        tabs = findViewById(R.id.tabs);
        viewpager = findViewById(R.id.viewpager);


>>>>>>> origin/master
        init();
    }

    void init() {

        MyViewPagerAdapter adapter = new MyViewPagerAdapter(getSupportFragmentManager());
        viewpager.setAdapter(adapter);
        viewpager.setAdapter(adapter);
        tabs.setupWithViewPager(viewpager);

    }

    @Override
    public void sendData(String message) {
        String tag = "android:switcher:" + R.id.viewpager + ":" + 1;
        FragmentMailBox f = (FragmentMailBox) getSupportFragmentManager().findFragmentByTag(tag);
        f.displayReceivedData(message);
        tabs.getTabAt(1).select();
    }
<<<<<<< HEAD

    public void checkConnection() {

        ConnectivityManager cm = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        if (null == activeNetwork) {
            //Toast.makeText(this, "İnternete bağlı değilsiniz", Toast.LENGTH_SHORT).show();
            createCancelProgressDialog("İnternet bağlantısı yok", "Lütfen internet bağlantınızı kontrol ediniz..!", "Tamam");
        }
=======

    public void checkConnection() {
>>>>>>> origin/master

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
    public void onBackPressed()
    {
        if (tabs.getSelectedTabPosition()==1)
        {
            tabs.getTabAt(0).select();

        }
        else
            finish();
    }

<<<<<<< HEAD
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
=======


>>>>>>> origin/master


}


