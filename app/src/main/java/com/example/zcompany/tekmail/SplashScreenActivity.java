package com.example.zcompany.tekmail;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
<<<<<<< HEAD
import android.content.Context;
=======
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
>>>>>>> origin/master
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
<<<<<<< HEAD
=======
import android.widget.QuickContactBadge;
>>>>>>> origin/master
import android.widget.TextView;

public class SplashScreenActivity extends AppCompatActivity {
    private static int SPLASH_SCREEN = 2500;

    Animation topAnim, bottomAnim;
<<<<<<< HEAD
    ImageView imageview, imageviewConnectionsClose;
=======
    ImageView imageview,imageviewConnectionsClose;
>>>>>>> origin/master
    TextView textViewWelcome;
    TextView textViewTemp;
    Dialog epicDialog;
    Button buttonConnectionsOK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);
        epicDialog = new Dialog(this);


        imageview = findViewById(R.id.imageview);
        textViewWelcome = findViewById(R.id.textViewWelcome);
        textViewTemp = findViewById(R.id.textViewTemp);


        imageview.setAnimation(topAnim);
        textViewWelcome.setAnimation(bottomAnim);
        textViewTemp.setAnimation(bottomAnim);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                checkConnection();
<<<<<<< HEAD
=======
                //Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                //startActivity(intent);
>>>>>>> origin/master
            }
        }, SPLASH_SCREEN);
    }

<<<<<<< HEAD
    public void showPopup() {
        epicDialog.setContentView(R.layout.epic_popup_nointernet);
        buttonConnectionsOK = epicDialog.findViewById(R.id.buttonConnectionsOK);
        imageviewConnectionsClose = epicDialog.findViewById(R.id.imageviewConnectionsClose);
=======
    public void showPopup()
    {
        epicDialog.setContentView(R.layout.epic_popup_nointernet);
        buttonConnectionsOK=epicDialog.findViewById(R.id.buttonConnectionsOK);
        imageviewConnectionsClose=epicDialog.findViewById(R.id.imageviewConnectionsClose);
>>>>>>> origin/master
        imageviewConnectionsClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                epicDialog.dismiss();
                finish();
                System.exit(0);
            }
        });
        epicDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        epicDialog.show();
        buttonConnectionsOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                epicDialog.dismiss();
<<<<<<< HEAD
                startActivity(new Intent(getApplicationContext(), OfflineActivity.class));
                finish();
=======
                startActivity(new Intent(getApplicationContext(),OfflineActivity.class));
                finish();


>>>>>>> origin/master
            }
        });

    }
<<<<<<< HEAD

=======
>>>>>>> origin/master
    public void checkConnection() {

        ConnectivityManager cm = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        if (null == activeNetwork)
            showPopup();

<<<<<<< HEAD
        else {
=======
         else {
>>>>>>> origin/master
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }
    }

}
