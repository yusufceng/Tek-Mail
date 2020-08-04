package com.example.zcompany.tekmail;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FragmentMailBox extends Fragment {
    private static final int LONG_DELAY = 3000;
    private static final int SHORT_DELAY = 2000;

    private View view;
    private RecyclerView rv;
    private TextView textViewMailshere;
    private SwipeRefreshLayout swipeRefresh;
    private MailAdapter mailAdapteradapter;
    private ArrayList<Mail> mailArrayList;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mail_box, container, false);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i("gecis","yapildi");

        sp = getActivity().getSharedPreferences("Where", getActivity().MODE_PRIVATE);
        editor = sp.edit();
        init();
        mailRead(sp.getString("MailID", "none"));
        Log.e(sp.getString("MailID","none")," ");

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                dispachRefresh();
                Toast.makeText(getContext(), R.string.page_is_refreshing, Toast.LENGTH_SHORT).show();
                showAToast(getResources().getString(R.string.page_is_refreshed));
            }
        });

    }

    protected void displayReceivedData(String message) {
        Log.e("mesajın", message);
        init();
        mailRead(message);
    }

    void init() {

        rv = view.findViewById(R.id.rv);
        textViewMailshere = view.findViewById(R.id.textViewMailshere);
        swipeRefresh = view.findViewById(R.id.swipeRefresh);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        mailArrayList = new ArrayList<>();
        mailAdapteradapter = new MailAdapter(getContext(), mailArrayList);
        rv.setAdapter(mailAdapteradapter);
        rv.addItemDecoration(new DividerItemDecoration(getContext(), 1));
        mailAdapteradapter.notifyDataSetChanged();

    }


    void mailRead(String message) {

        if (message.equals("none"))
            Log.e("mail addres error", "mail adresi girilmedi yada olusturulamadı");
        else {
            DatabaseReference mailYolu = FirebaseDatabase.getInstance().getReference(message);
            mailYolu.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    mailArrayList.clear();
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Mail mail = snapshot.getValue(Mail.class);
                        mail.setMail_uid(snapshot.getKey());
                        mailArrayList.add(mail);
                        textViewMailshere.setVisibility(View.INVISIBLE);
                    }
                    mailAdapteradapter.notifyDataSetChanged();
                    if (mailArrayList.size() == 0)
                        textViewMailshere.setVisibility(View.VISIBLE);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
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
        init();
        mailRead(sp.getString("MailID", "none"));
        Log.e(sp.getString("MailID","none")," ");

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeRefresh.setRefreshing(false);
            }
        }, SHORT_DELAY);
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
}



