package com.example.admins.blackfund.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.admins.blackfund.activities.OverviewFragment;

/**
 * Created by Son Hoang on 10/24/2017.
 */

public class OverviewPagerAdapter extends FragmentPagerAdapter {
    private static final int PAGE_COUNT = 2;
    private String tabTitles[] = new String[]{"Last month", "This month"};

    public OverviewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new OverviewFragment();
            case 1:
                return new OverviewFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
