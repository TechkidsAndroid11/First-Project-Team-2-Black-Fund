package com.example.admins.blackfund.activities;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admins.blackfund.R;
import com.example.admins.blackfund.databases.BlackFundDatabase;

import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Locale;

import static com.example.admins.blackfund.adapters.OverviewPagerAdapter.OVERVIEW_KEY;

/**
 * Created by Son Hoang on 10/24/2017.
 */

public class OverviewFragment extends Fragment {
    private TextView tvTotalIncome;
    private TextView tvTotalExpense;
    private TextView tvDifference;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View overviewTab = inflater.inflate(R.layout.overview_fragment_tab, container, false);
        tvTotalIncome = overviewTab.findViewById(R.id.tv_total_income);
        tvTotalExpense = overviewTab.findViewById(R.id.tv_total_expense);
//        tvDifference = overviewTab.findViewById(R.id.tv_difference);

        return overviewTab;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onStart() {
        super.onStart();
        int currentMonth;
        Bundle bundle = getArguments();
        if (bundle.getBoolean(OVERVIEW_KEY, true)) {
            currentMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
        } else {
            currentMonth = Calendar.getInstance().get(Calendar.MONTH);
        }

        //set total income
        int intTotalIncome = BlackFundDatabase.getInstance(getActivity()).calculateIncome(currentMonth);
  //      String totalIncome = String.valueOf(intTotalIncome);
        tvTotalIncome.setText(NumberFormat.getNumberInstance(Locale.US).format(intTotalIncome));

        //set total expense
        int intTotalExpense = BlackFundDatabase.getInstance(getActivity()).calculateExpense(currentMonth);
     //   String totalExpense = String.valueOf(intTotalExpense);
        tvTotalExpense.setText(NumberFormat.getNumberInstance(Locale.US).format(intTotalExpense));

        //set difference
        int intTotalDifference = intTotalIncome - intTotalExpense;
     //   String totalDifference = String.valueOf(intTotalDifference);
//        if (intTotalDifference <= 0){
//            tvDifference.setTextColor(Color.parseColor("#dc000d"));
//        } else {
//            tvDifference.setTextColor(Color.parseColor("#008f24"));
//        }
//        tvDifference.setText(NumberFormat.getNumberInstance(Locale.US).format(intTotalDifference));
    }
}
