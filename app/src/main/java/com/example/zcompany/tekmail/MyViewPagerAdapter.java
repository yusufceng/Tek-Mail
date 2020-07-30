package com.example.zcompany.tekmail;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

class MyViewPagerAdapter extends FragmentPagerAdapter {
    private final List<Fragment> fragmentList = new ArrayList<>();
    private final List<String> fragmentBaslikList = new ArrayList<>();

    public MyViewPagerAdapter(FragmentManager manager) {
        super(manager);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position == 0) {
            fragment = new FragmentMailAdrress();
        } else if (position == 1) {
            fragment = new FragmentMailBox();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        if (position == 0) {
            title = "AnaSayfa";
        } else if (position == 1) {
            title = "Gelen Kutusu";
        }
        return title;
    }

    public void fragmentEkle(Fragment fragment, String baslik) {
        fragmentList.add(fragment);
        fragmentBaslikList.add(baslik);
    }


}
