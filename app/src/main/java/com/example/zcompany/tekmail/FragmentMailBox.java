package com.example.zcompany.tekmail;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
<<<<<<< HEAD
=======
import android.widget.Toast;
>>>>>>> origin/master

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

<<<<<<< HEAD
=======

>>>>>>> origin/master
public class FragmentMailBox extends Fragment {

    private View view;
    private RecyclerView rv;
    private TextView textViewMailshere;
<<<<<<< HEAD
    private MailAdapter mailAdapteradapter;
=======
    MailAdapter mailAdapteradapter;
    private TextView textViewDefault;
>>>>>>> origin/master
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

<<<<<<< HEAD
        sp = getActivity().getSharedPreferences("Where", getActivity().MODE_PRIVATE);
        editor = sp.edit();
        init();
        mailRead(sp.getString("MailID", "none"));
=======
        sp=getActivity().getSharedPreferences("Where",getActivity().MODE_PRIVATE);
        editor=sp.edit();
        init();
        mailRead(sp.getString("MailID","none"));
>>>>>>> origin/master

    }

    protected void displayReceivedData(String message) {
        Log.e("mesajın", message);
        init();
        mailRead(message);
    }

    void init() {

        rv = view.findViewById(R.id.rv);
        textViewMailshere = view.findViewById(R.id.textViewMailshere);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        mailArrayList = new ArrayList<>();
        mailAdapteradapter = new MailAdapter(getContext(), mailArrayList);
        rv.setAdapter(mailAdapteradapter);
        rv.addItemDecoration(new DividerItemDecoration(getContext(), 1));

<<<<<<< HEAD
=======


>>>>>>> origin/master
    }

    void mailRead(String message) {

        if (message.equals("none"))
<<<<<<< HEAD
            Log.e("mail addres error", "mail adresi girilmedi yada olusturulamadı");
        else {
=======
            Log.e("mail addres error","mail adresi girilmedi yada olusturulamadı");
        else
        {
>>>>>>> origin/master
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
<<<<<<< HEAD
                    }
                    mailAdapteradapter.notifyDataSetChanged();
                    if (mailArrayList.size() == 0)
=======

                    }
                    mailAdapteradapter.notifyDataSetChanged();
                    if (mailArrayList.size()==0)
>>>>>>> origin/master
                        textViewMailshere.setVisibility(View.VISIBLE);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }


}
