package com.example.zcompany.tekmail;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TabLayout tabs;
    private ViewPager viewpager;
    private Button buttonCreate;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

    }
    void init()
    {
        tabs=findViewById(R.id.tabs);
        viewpager=findViewById(R.id.viewpager);




        MyViewPagerAdapter adapter=new MyViewPagerAdapter(getSupportFragmentManager());
        adapter.fragmentEkle(new FragmentMailAdrress(),"Home");
        adapter.fragmentEkle(new FragmentMailBox(),"Mail Box");

        viewpager.setAdapter(adapter);
        tabs.setupWithViewPager(viewpager);




        //Mail

    }



    class MyViewPagerAdapter extends FragmentPagerAdapter
    {
        private final List<Fragment> fragmentList=new ArrayList<>();
        private final List<String> fragmentBaslikList=new ArrayList<>();

        public  MyViewPagerAdapter(FragmentManager manager)
        {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentBaslikList.get(position);
        }

        public void fragmentEkle(Fragment fragment,String baslik)
        {
            fragmentList.add(fragment);
            fragmentBaslikList.add(baslik);
        }
    }
}
