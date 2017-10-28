package com.example.admins.blackfund.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admins.blackfund.R;

import static com.example.admins.blackfund.adapters.OverviewPagerAdapter.OVERVIEW_KEY;

/**
 * Created by Son Hoang on 10/24/2017.
 */

public class OverviewFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View overviewTab = inflater.inflate(R.layout.overview_fragment_tab, container, false);
//        TextView tvDemo = overviewTab.findViewById(R.id.tv_demo);
//
//        if (getArguments().getBoolean(OVERVIEW_KEY)){
//            tvDemo.setText("This month");
//        } else {
//            tvDemo.setText("Last month");
//        }

        return overviewTab;
    }
}
