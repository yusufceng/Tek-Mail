package com.example.zcompany.tekmail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class OfflineActivity extends AppCompatActivity {
<<<<<<< HEAD
    private static int CONNECTION_OK = 2000;
=======
    private  static int CONNECTION_OK = 2000;
>>>>>>> origin/master
    private TextView textViewConnectStatus;
    private Button buttonTryAgain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_offline);
        checkConnection();
<<<<<<< HEAD
=======

>>>>>>> origin/master
    }


    public void checkConnection() {
        buttonTryAgain = findViewById(R.id.buttonTryAgain);
        textViewConnectStatus = findViewById(R.id.textViewConnectStatus);
<<<<<<< HEAD
        final ImageView imageviewConnectionsState = findViewById(R.id.imageviewConnectionsState);
=======
        final ImageView imageviewConnectionsState=findViewById(R.id.imageviewConnectionsState);
>>>>>>> origin/master

        buttonTryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectivityManager cm = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
<<<<<<< HEAD
                if (null == activeNetwork) {
                    Toast.makeText(OfflineActivity.this, "Bağlantı kurulamadı tekrar deneyiniz..!", Toast.LENGTH_SHORT).show();
                    textViewConnectStatus.setText(R.string.connections_try);
                } else {
=======
                if (null == activeNetwork)
                {
                    Toast.makeText(OfflineActivity.this, "Bağlantı kurulamadı tekrar deneyiniz..!", Toast.LENGTH_SHORT).show();
                    textViewConnectStatus.setText(R.string.connections_try);
                }


                else
                {
>>>>>>> origin/master
                    imageviewConnectionsState.setImageResource(R.drawable.ic_baseline_wifi_24);
                    textViewConnectStatus.setText(R.string.connection_stateOK);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            finish();
<<<<<<< HEAD
                        }
                    }, CONNECTION_OK);
=======

                        }
                    }, CONNECTION_OK);



>>>>>>> origin/master
                }
            }
        });

<<<<<<< HEAD
=======

>>>>>>> origin/master
    }
}
