package com.example.zcompany.tekmail;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FragmentMailAdrress extends Fragment {

    SendMessage SM;

    private static final int LONG_DELAY = 3000;
    private static final int SHORT_DELAY = 1000;
    private ClipboardManager copyPnao;
    private ClipData clipData;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private String random_mailID;
    private ArrayList<RandomMail> mailArrayList;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(
                R.layout.fragment_mail_adrress, container, false);

        mailArrayList=new ArrayList<>();

        sp=getActivity().getSharedPreferences("Where",getActivity().MODE_PRIVATE);
        editor=sp.edit();
        if (sp.getString("MailID","none").equals("none"))
        {
            //random_mailID="admin";
            getRandomMailIDFromDB();
            Log.i(random_mailID," ");
            getActivity().startActivity(new Intent(getContext(),MainActivity.class));
            getActivity().finish();

        }
        else
            random_mailID=sp.getString("MailID","none");




        return rootView;

    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button buttonCreate = view.findViewById(R.id.buttonCreate);
        Button buttonSave = view.findViewById(R.id.buttonSave);
        final TextInputLayout mailID = view.findViewById(R.id.mailID);
        final TextInputLayout mailIDRandom = view.findViewById(R.id.mailIDRandom);
        final TextInputLayout mailDomain = view.findViewById(R.id.mailDomain);
        final LinearLayout LinearLayoutTakeDomain = view.findViewById(R.id.LinearLayoutTakeDomain);
        final LinearLayout LinearLayoutCopy = view.findViewById(R.id.LinearLayoutCopy);
        final LinearLayout LinearLayoutRefresh = view.findViewById(R.id.LinearLayoutRefresh);
        final LinearLayout LinearLayoutNew = view.findViewById(R.id.LinearLayoutNew);

        random_mailID=sp.getString("MailID","none");
        mailIDRandom.getEditText().setText(random_mailID+"@tek-mail.net");
        copyPnao = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);

        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayoutTakeDomain.setVisibility(View.VISIBLE);

            }
        });

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mailID.getEditText().getText().toString().equals(""))
                    Toast.makeText(getContext(), "Lütfen boş bırakmayınız..!", Toast.LENGTH_SHORT).show();
                else {
                    hideKeyboard();
                    SM.sendData(mailID.getEditText().getText().toString().trim());
                    Toast.makeText(getContext(), "Mail Adresiniz olusturuluyor lütfen bekleyiniz...", Toast.LENGTH_SHORT).show();
                    showAToast("Geçici Mail Adresiniz Kullanıma Hazır!");
                    spMailIDRegister(mailID.getEditText().getText().toString());
                    mailIDRandom.getEditText().setText(mailID.getEditText().getText().toString()+"@tek-mail.net");
                }

            }
        });


        mailDomain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Şimdilik kullanıma açık tek alan adımız var , yakında daha fazlası aktif olacak", Toast.LENGTH_SHORT).show();
            }
        });
        LinearLayoutNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getRandomMailIDFromDB();
                refreshActivty();
            }
        });

        LinearLayoutCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String copyiedString;
                copyiedString = (mailIDRandom.getEditText().getText()).toString();

                clipData = (ClipData) ClipData.newPlainText("text", copyiedString);
                copyPnao.setPrimaryClip(clipData);

                if (copyiedString.equals(""))
                    Toast.makeText(getContext(), "Kopyalama başarısız", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getContext(), "\"" + copyiedString + "\"" + " Panoya Kopyalandı", Toast.LENGTH_SHORT).show();
            }
        });
        LinearLayoutRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshActivty();


            }
        });

    }

    interface SendMessage {
        void sendData(String message);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            SM = (SendMessage) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException("Error in retrieving data. Please try again");
        }
    }

    public void showAToast(final String st) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getActivity(), st, Toast.LENGTH_SHORT).show();
            }
        }, SHORT_DELAY);
    }

    public void hideKeyboard() {
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager inputManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }


    public void getRandomMailIDFromDB()
    {
        DatabaseReference ref=FirebaseDatabase.getInstance().getReference("Mail_ID").child("free");
        Query queryUid=ref.orderByKey().limitToFirst(1);
        queryUid.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot datas : dataSnapshot.getChildren()) {
                    String key=datas.getKey();
                    RandomMail randomMail=datas.getValue(RandomMail.class);
                    mailArrayList.add(randomMail);
                    //String mailadres=randomMail.getMail_Address();
                    setrandom_mailID(mailArrayList.get(0).getMail_Address());
                    deletesSelectedMailIDFromDB(datas.getKey());
                    //updateUsedMailIDFromFreeMailID(mailArrayList.get(0));

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void deletesSelectedMailIDFromDB(String uuid)
    {
        DatabaseReference pathID = FirebaseDatabase.getInstance().getReference("Mail_ID").child("free").child(uuid);
        pathID.removeValue();
        return;
    }

    public void setrandom_mailID(String str)
    {
        spMailIDRegister(str);
        Log.e("1","boş oldugu için çalıştı");
    }

    public void spMailIDRegister(String random_mailID)
    {
        SharedPreferences sp=getActivity().getSharedPreferences("Where",getActivity().MODE_PRIVATE);
        SharedPreferences.Editor e=sp.edit();
        e.putString("MailID",random_mailID);
        e.commit();
        getActivity().startActivity(new Intent(getContext(),MainActivity.class));
        getActivity().finish();
    }
    public void updateUsedMailIDFromFreeMailID(RandomMail randomMail)
    {
        DatabaseReference pathID = FirebaseDatabase.getInstance().getReference("Mail_ID").child("used");
        pathID.push().setValue(randomMail);
        return;

    }

    public void refreshActivty()
    {
        getActivity().startActivity(new Intent(getContext(),MainActivity.class));
        getActivity().finish();
    }



}
