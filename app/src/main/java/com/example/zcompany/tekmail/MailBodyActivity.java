package com.example.zcompany.tekmail;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MailBodyActivity extends AppCompatActivity {
    private WebView webview;
    MailAdapter mailAdapteradapter;
    public ArrayList<Mail> mailArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail_body);
        mailArrayList = new ArrayList<>();
        String str = getIntent().getStringExtra("uid");
        mailRead(str);


        webview = findViewById(R.id.webview);
        WebSettings webSettings = webview.getSettings();
        webview.getSettings().setJavaScriptEnabled(true);
        webview.setWebViewClient(new WebViewClient());

        //String html= Base64.encodeToString(mailArrayList.get(0).getMail_Body().getBytes(),Base64.NO_PADDING);
        //webview.loadData(html,"text/html","base64");
        int siz=mailArrayList.size();
        Log.e("m "," "+siz);
    }


    void mailRead(final String str)
    {
        DatabaseReference mailYolu= FirebaseDatabase.getInstance().getReference("admin");
        mailYolu.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mailArrayList.clear();
                for (DataSnapshot snapshot:dataSnapshot.getChildren())
                {
                    if (snapshot.getKey().equals(str))
                    {
                        Log.e("Mail bulundu ",snapshot.getKey());
                        Mail mail=snapshot.getValue(Mail.class);
                        mail.setMail_uid(snapshot.getKey());
                        mailArrayList.add(mail);
                        //Toast.makeText(getContext(), mail.getMail_uid(), Toast.LENGTH_SHORT).show();

                    }

                }
                //mailAdapteradapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
