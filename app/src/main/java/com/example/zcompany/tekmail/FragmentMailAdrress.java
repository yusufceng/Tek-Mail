package com.example.zcompany.tekmail;

import android.app.TabActivity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.renderscript.Sampler;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.example.zcompany.tekmail.R.string.page_is_refreshed;

public class FragmentMailAdrress extends Fragment {

    SendMessage SM;

    private static final int LONG_DELAY = 3000;
    private static final int SHORT_DELAY = 2000;
    private ClipboardManager copyPnao;
    private ClipData clipData;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private String random_mailID;
    private ArrayList<RandomMail> mailArrayList;
    private SwipeRefreshLayout swipeRefresh;
    private TextInputLayout mailIDRandom;
    private Button buttonCreate;
    private TextInputLayout mailID;
    private TextInputLayout mailDomain;
    private LinearLayout LinearLayoutTakeDomain;
    private LinearLayout LinearLayoutCopy;
    private LinearLayout LinearLayoutRefresh;
    private LinearLayout LinearLayoutNew;
    private LinearLayout LinearLayoutDelete;
    private Button buttonSave;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(
                R.layout.fragment_mail_adrress, container, false);

        mailArrayList = new ArrayList<>();
        swipeRefresh = rootView.findViewById(R.id.swipeRefresh);
        buttonCreate = rootView.findViewById(R.id.buttonCreate);
        buttonSave = rootView.findViewById(R.id.buttonSave);
        mailID = rootView.findViewById(R.id.mailID);
        mailIDRandom = rootView.findViewById(R.id.mailIDRandom);
        mailDomain = rootView.findViewById(R.id.mailDomain);
        LinearLayoutTakeDomain = rootView.findViewById(R.id.LinearLayoutTakeDomain);
        LinearLayoutCopy = rootView.findViewById(R.id.LinearLayoutCopy);
        LinearLayoutRefresh = rootView.findViewById(R.id.LinearLayoutRefresh);
        LinearLayoutNew = rootView.findViewById(R.id.LinearLayoutNew);
        LinearLayoutDelete = rootView.findViewById(R.id.LinearLayoutDelete);





        sp = getActivity().getSharedPreferences("Where", getActivity().MODE_PRIVATE);
        editor = sp.edit();
        if (sp.getString("MailID", "none").equals("none")) {
            getRandomMailIDFromDB();
            Log.i(random_mailID, " ");
            getActivity().startActivity(new Intent(getContext(), MainActivity.class));
            getActivity().finish();

        } else
            random_mailID = sp.getString("MailID", "none");

        return rootView;

    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        random_mailID = sp.getString("MailID", "none");
        mailIDRandom.getEditText().setText(random_mailID + "@tek-mail.net");
        copyPnao = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                dispachRefresh();
                Toast.makeText(getContext(),R.string.page_is_refreshing, Toast.LENGTH_SHORT).show();
                showAToast(getResources().getString(R.string.page_is_refreshed));
            }
        });

        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (LinearLayoutTakeDomain.getVisibility()==View.INVISIBLE)
                    LinearLayoutTakeDomain.setVisibility(View.VISIBLE);
                else
                    LinearLayoutTakeDomain.setVisibility(View.INVISIBLE);
            }
        });

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mailID.getEditText().getText().toString().equals(""))
                    Toast.makeText(getContext(), R.string.do_not_empty, Toast.LENGTH_SHORT).show();
                else {
                    hideKeyboard();
                    SM.sendData(mailID.getEditText().getText().toString().trim());
                    Toast.makeText(getContext(), R.string.mail_creating, Toast.LENGTH_SHORT).show();
                    showAToast(getResources().getString(R.string.mail_created));
                    spMailIDRegister(mailID.getEditText().getText().toString());
                    mailIDRandom.getEditText().setText(mailID.getEditText().getText().toString() + "@tek-mail.net");
                    LinearLayoutTakeDomain.setVisibility(View.INVISIBLE);
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
                Toast.makeText(getContext(), R.string.mail_creating, Toast.LENGTH_SHORT).show();
                showAToast(getResources().getString(R.string.new_random_mail));
                //SM.sendData(mailID.getEditText().getText().toString().trim());

            }
        });
        LinearLayoutDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mailID.getEditText().getText().equals(""))
                    showAToast(getResources().getString(R.string.do_not_empty));
                else
                    removeMailIDFromDB(mailID.getEditText().getText());
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
                    Toast.makeText(getContext(),getResources().getString(R.string.copy_fail), Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getContext(), "\"" + copyiedString + "\"" + getResources().getString(R.string.copy_succ), Toast.LENGTH_SHORT).show();
            }
        });
        LinearLayoutRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispachRefresh();
                Toast.makeText(getContext(), R.string.page_is_refreshing, Toast.LENGTH_SHORT).show();
                showAToast(getResources().getString(R.string.page_is_refreshed));
            }
        });

    }

    private void removeMailIDFromDB(Editable text) {
        DatabaseReference pathID = FirebaseDatabase.getInstance().getReference().child(text.toString());
        pathID.removeValue();
        getRandomMailIDFromDB();

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
                Toast.makeText(getActivity(), (String)st, Toast.LENGTH_SHORT).show();
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


    public void getRandomMailIDFromDB() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Mail_ID").child("free");
        Query query = ref.orderByKey().limitToFirst(1);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mailArrayList.clear();
                for (DataSnapshot datas : dataSnapshot.getChildren()) {
                    RandomMail randomMail = datas.getValue(RandomMail.class);
                    mailArrayList.add(randomMail);
                    setrandom_mailID(mailArrayList.get(0).getMail_Address());
                    deletesSelectedMailIDFromDB(datas.getKey());
                    //updateUsedMailIDFromFreeMailID(mailArrayList.get(0));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    public void deletesSelectedMailIDFromDB(String uuid) {
        DatabaseReference pathID = FirebaseDatabase.getInstance().getReference("Mail_ID").child("free").child(uuid);
        pathID.removeValue();
        return;
    }

    public void setrandom_mailID(String str) {
        spMailIDRegister(str);

    }

    public void spMailIDRegister(String random_mailID) {
        SharedPreferences sp = getActivity().getSharedPreferences("Where", getActivity().MODE_PRIVATE);
        SharedPreferences.Editor e = sp.edit();
        e.putString("MailID", random_mailID);
        e.commit();
        dispachRefresh();
    }

    public void updateUsedMailIDFromFreeMailID(RandomMail randomMail) {
        DatabaseReference pathID = FirebaseDatabase.getInstance().getReference("Mail_ID").child("used");
        pathID.push().setValue(randomMail);
        return;
    }

    private SwipeRefreshLayout.OnRefreshListener refreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            dispachRefresh();
        }
    };

    private void dispachRefresh() {
        swipeRefresh.setRefreshing(true);
        sp = getActivity().getSharedPreferences("Where", getActivity().MODE_PRIVATE);
        editor = sp.edit();
        random_mailID = sp.getString("MailID", "none");
        mailIDRandom.getEditText().setText(random_mailID + "@tek-mail.net");
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeRefresh.setRefreshing(false);
            }
        }, SHORT_DELAY);
    }
}
