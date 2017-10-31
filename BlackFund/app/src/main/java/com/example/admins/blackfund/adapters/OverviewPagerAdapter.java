package com.example.admins.blackfund.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.admins.blackfund.activities.OverviewFragment;

/**
 * Created by Son Hoang on 10/24/2017.
 */

public class OverviewPagerAdapter extends FragmentPagerAdapter {
    private static final int PAGE_COUNT = 2;
    public static final String OVERVIEW_KEY = "overview_key";
    private String tabTitles[] = new String[]{"Last month", "This month"};

    public OverviewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return setOverviewTabData(false);
            case 1:
                return setOverviewTabData(true);
        }
        return null;
    }

    private OverviewFragment setOverviewTabData(boolean trueMonth) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(OVERVIEW_KEY, trueMonth);
        OverviewFragment overviewFragment = new OverviewFragment();
        overviewFragment.setArguments(bundle);
        return overviewFragment;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }
}
