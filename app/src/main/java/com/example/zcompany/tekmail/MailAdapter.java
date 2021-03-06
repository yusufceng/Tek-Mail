package com.example.zcompany.tekmail;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MailAdapter extends RecyclerView.Adapter<MailAdapter.CardViewTasarimNesneleriniTutucu> {
    private Context mContext;
    private List<Mail> mailList;

    public MailAdapter(Context mContext, List<Mail> mailList) {
        this.mContext = mContext;
        this.mailList = mailList;
    }

    public class CardViewTasarimNesneleriniTutucu extends RecyclerView.ViewHolder {
        public LinearLayout ll;
        public TextView textViewName;
        public TextView textViewDate;
        public TextView textViewSubject;

        public CardViewTasarimNesneleriniTutucu(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewDate = itemView.findViewById(R.id.textViewDate);
            textViewSubject = itemView.findViewById(R.id.textViewSubject);
            ll = itemView.findViewById(R.id.ll);
        }
    }

    @NonNull
    @Override
    public CardViewTasarimNesneleriniTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_tasarim_gelen_kutusu, parent, false);
        return new CardViewTasarimNesneleriniTutucu(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CardViewTasarimNesneleriniTutucu holder, int position) {
        final Mail mail = mailList.get(position);

        if (mail.getMail_From_Name().equals(""))
            holder.textViewName.setText((mail.getMail_From_Address()));
        else
            holder.textViewName.setText(mail.getMail_From_Name());


        holder.textViewDate.setText(mail.getMail_Date());
        holder.textViewSubject.setText(mail.getMail_Subject());
        holder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, R.string.mail_load, Toast.LENGTH_SHORT).show();

                Intent yeniIntent = new Intent(mContext, MailBodyActivity.class);
                yeniIntent.putExtra("uid", mail.getMail_uid());
                yeniIntent.putExtra("alias_name", mail.getMail_Address());
                mContext.startActivity(yeniIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mailList.size();
    }

}
