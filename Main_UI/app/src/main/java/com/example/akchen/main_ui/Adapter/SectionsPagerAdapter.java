package com.example.akchen.main_ui.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.akchen.main_ui.Fragment.MainUIFragment;
import com.example.akchen.main_ui.R;

import java.util.ArrayList;

/**
 * Created by AkChen on 2016/7/9 0009.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private static ArrayList<MainUIFragment> fragmentsList = new ArrayList<>();

    public static ArrayList<MainUIFragment> getFragmentsList() {
        return fragmentsList;
    }

    private static FragmentManager fragmentManager;

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
        fragmentManager = fm;

    }

    @Override
    public Fragment getItem(int position) {
        if (position != 0) {
            fragmentManager.beginTransaction()
                    .replace(R.id.container, fragmentsList.get(position))
                    .commit();
        }
        return fragmentsList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentsList.size();
    }
}
