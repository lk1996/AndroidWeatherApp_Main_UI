package com.example.akchen.main_ui.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.akchen.main_ui.Fragment.MainUIFragment;

import java.util.ArrayList;

/**
 * Created by AkChen on 2016/7/9 0009.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private static   ArrayList<MainUIFragment> fragmentsList=new ArrayList<>();

    public static ArrayList<MainUIFragment> getFragmentsList() {
        return fragmentsList;
    }

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    @Override
    public  Fragment getItem(int position) {

        return fragmentsList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentsList.size();
    }
}
