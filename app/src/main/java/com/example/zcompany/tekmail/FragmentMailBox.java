package com.example.zcompany.tekmail;

import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

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
        view = inflater.inflate(R.layout.fragment_mail_box,container,false);

        //webview=view.findViewById(R.id.webview);
        //WebSettings webSettings=webview.getSettings();
        //webview.getSettings().setJavaScriptEnabled(true);
        //String str=  "<div dir=\"auto\"></div><br><div class=\"gmail_quote\"><div dir=\"ltr\" class=\"gmail_attr\">---------- Forwarded message ---------<br>Gönderen: <strong class=\"gmail_sendername\" dir=\"auto\">Future Project</strong> <span dir=\"auto\">&lt;<a href=\"mailto:futureproject@gizemfrit.com\">futureproject@gizemfrit.com</a>&gt;</span><br>Date: 16 Tem 2020 Per 10:37<br>Subject: The Future Project Staj Programı Başvuru Sonucu<br>To: <br></div><br><br>\n\n\n\n\n\n<div lang=\"TR\" link=\"#0563C1\" vlink=\"#954F72\">\n<div class=\"m_-6496950930628552239WordSection1\">\n<p class=\"MsoNormal\">Sevgili Future Project Adayımız,<u></u><u></u></p>\n<p class=\"MsoNormal\"><u></u> <u></u></p>\n<p class=\"MsoNormal\">Future Project 2020 staj programına başvurduğun için teşekkür ederiz. Başvurun incelenmiş, ancak bu aşamada, belirlenmiş olan kriterlere göre, olumlu değerlendirme yapılamamıştır. İleride doğabilecek uygun pozisyonlarda değerlendirilmek\n üzere bilgilerin veri tabanımıza kaydedilmiştir.<br>\n<br>\nBaşvurun ve gösterdiğin ilgi için teşekkür eder, kariyerinde başarılar dileriz.<u></u><u></u></p>\n<p class=\"MsoNormal\"><u></u> <u></u></p>\n<p class=\"MsoNormal\">Saygılarımızla,<u></u><u></u></p>\n<p class=\"MsoNormal\"><b>İnsan Kaynakları<u></u><u></u></b></p>\n</div>\n<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n<tbody>\n<tr>\n<td><a href=\"https://www.linkedin.com/company/gizemfrit/\" target=\"_blank\" rel=\"noreferrer\"><img src=\"http://www.gizemfrit.com/gizemmail.jpg\" alt=\"GizemSosial\">\n</a></td></tr>\n\n<tr>\n<td>_________________________________________________________________________________________________________________</td>\n</tr>\n<tr>\n<td><span style=\"color:#696969\"><strong><span style=\"font-family:verdana,geneva,sans-serif\"><span style=\"font-size:9px\">YASAL UYARI</span></span></strong></span></td>\n</tr>\n<tr>\n<td><span style=\"color:#696969\"><span style=\"font-family:verdana,geneva,sans-serif\"><span style=\"font-size:9px\">Bu e-posta ve içeriği kişiye özel ve gizli bilgiler içerebilir. Eğer mesajın alıcısı veya alıcısına iletmekle yetkili değilseniz (yada bu e-postayı\n yanlışlıkla aldıysanız), bu mesajı çoğaltmak, dağıtmak, açıklamak dahil olmak üzere herhangi bir nedenle kullanmamanız gerektiğini, aksi davranışın hukuka aykırılık teşkil edebileceğini bildiririz. Eğer bu mesajı yanlışlıkla aldıysanız, lütfen göndericiye\n e-posta ile bildirerek siliniz</span></span></span></td>\n</tr>\n<tr>\n<td><span style=\"color:#696969\"><strong><span style=\"font-family:verdana,geneva,sans-serif\"><span style=\"font-size:9px\">E–MAIL DISCLAIMER</span></span></strong></span></td>\n</tr>\n<tr>\n<td><span style=\"color:#696969\"><span style=\"font-family:verdana,geneva,sans-serif\"><span style=\"font-size:9px\">This email and its contents may contain information that is privileged and confidential. If you are not an intended recipient,or the agent\n responsible for delivering this email to the intended recipient, you are hereby notified that any use, dissemination, distribution, or copying of this communication is strictly prohibited and may be unlawful. If you received this email in error, please notify\n the sender by replying to this email and delete the email sent in error.</span></span></span></td>\n</tr>\n</tbody>\n</table>\n<p> </p>\n</div>\n\n</div>";

        //webview.setWebViewClient(new WebViewClient());
        //String html= Base64.encodeToString(str.getBytes(),Base64.NO_PADDING);
        //webview.loadData(html,"text/html","base64");

        rv=view.findViewById(R.id.rv);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        mailArrayList=new ArrayList<>();
        mailAdapteradapter =new MailAdapter(getContext(),mailArrayList);
        rv.setAdapter(mailAdapteradapter);
        rv.addItemDecoration(new DividerItemDecoration(getContext(),1));


        mailRead();






        return view;}
    void mailRead()
    {
        DatabaseReference mailYolu= FirebaseDatabase.getInstance().getReference("admin");
        mailYolu.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mailArrayList.clear();
                for (DataSnapshot snapshot:dataSnapshot.getChildren())
                {
                    Mail mail=snapshot.getValue(Mail.class);
                    mail.setMail_uid(snapshot.getKey());
                    mailArrayList.add(mail);
                    //Toast.makeText(getContext(), mail.getMail_uid(), Toast.LENGTH_SHORT).show();
                }
                mailAdapteradapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
