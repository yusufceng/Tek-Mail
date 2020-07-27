package com.example.zcompany.tekmail;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MailBodyActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail_body);

        if (getIntent().hasExtra("uid"))
            findMail(getIntent().getStringExtra("uid"));
        else
            Log.e("", "");
    }


    void findMail(final String str) {
        DatabaseReference mailRef = FirebaseDatabase.getInstance().getReference("admin").child(str);
        mailRef.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Mail mail = dataSnapshot.getValue(Mail.class);
                generateWebView(mail);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void generateWebView(Mail mail) {
        WebView webview = findViewById(R.id.webview);
                webview.getSettings().setLoadWithOverviewMode(true);
        //webview.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING);
        webview.getSettings().setLoadWithOverviewMode(true);
        webview.getSettings().setBuiltInZoomControls(true);
        webview.getSettings().setDisplayZoomControls(false);


        webview.getSettings().setJavaScriptEnabled(true);
        webview.setWebViewClient(new WebViewClient());
        String html = Base64.encodeToString((
                "<p style=\"font-size:30px font-size: 3vw\"> "+mail.getMail_Subject()+"</p>"+
                "<p style=\"font-size:15px font-size: 3vw\"> "+mail.getMail_From_Name()+" - "+mail.getMail_From_Address()+"</p>"+
                "<p style=\"font-size:10px font-size: 3vw\"> "+mail.getMail_Date()+"</p>"+
                " "+mail.getMail_Body())
                .getBytes(), Base64.NO_PADDING);
       /* String html = Base64.encodeToString((
                mail.getMail_Subject()+ "</br>"+
                        mail.getMail_From_Name()+" - "+mail.getMail_From_Address()+
                        "</br>"+mail.getMail_Date()+
                        "</br>"+
                        " "+mail.getMail_Body())
                .getBytes(), Base64.NO_PADDING);*/

        webview.loadData(html, "text/html", "base64");
    }
}
