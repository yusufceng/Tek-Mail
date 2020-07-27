package com.example.zcompany.tekmail;

import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;



public class FragmentMailBox extends Fragment {

    private View view;
    private RecyclerView rv;
    MailAdapter mailAdapteradapter;
    private ArrayList<Mail> mailArrayList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mail_box, container, false);
        if (getActivity().getIntent().hasExtra("alias_name"))
            Log.i("has extra","bilgi geldi");
        else
            Log.i("get intent","iblgi gelmedi");

        rv = view.findViewById(R.id.rv);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        mailArrayList = new ArrayList<>();
        mailAdapteradapter = new MailAdapter(getContext(), mailArrayList);
        rv.setAdapter(mailAdapteradapter);
        rv.addItemDecoration(new DividerItemDecoration(getContext(), 1));


        mailRead();


        return view;
    }

    void mailRead() {


        DatabaseReference mailYolu = FirebaseDatabase.getInstance().getReference("admin");
        mailYolu.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mailArrayList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Mail mail = snapshot.getValue(Mail.class);
                    mail.setMail_uid(snapshot.getKey());
                    mailArrayList.add(mail);

                }
                mailAdapteradapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
