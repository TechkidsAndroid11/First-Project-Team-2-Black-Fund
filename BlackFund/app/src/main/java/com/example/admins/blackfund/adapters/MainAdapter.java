package com.example.admins.blackfund.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admins.blackfund.R;
import com.example.admins.blackfund.models.GhiChu;
import com.example.admins.blackfund.utils.ReadDataUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nguyen Duc Anh on 10/25/2017.
 */

public class MainAdapter extends ArrayAdapter<GhiChu> {
    private static final String TAG = MainAdapter.class.toString();
    private List<GhiChu> list;
    private Context context;
    private int resource;


    public MainAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<GhiChu> objects) {
        super(context, resource, objects);
        this.list = new ArrayList<>();
        this.context = context;
        this.resource = resource;
        this.list = objects;

    }

    @SuppressLint("ResourceType")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        convertView = layoutInflater.inflate(resource, parent, false);
        TextView tvDate = convertView.findViewById(R.id.tv_date);
        TextView tvDay = convertView.findViewById(R.id.tv_day);
        TextView tvMonthAndYear = convertView.findViewById(R.id.tv_month_and_year);
        ImageView ivReason = convertView.findViewById((R.id.iv_reason));
        TextView tvReason = convertView.findViewById(R.id.tv_ly_do);
        TextView tvMoneyOn = convertView.findViewById(R.id.tv_money_on);
        //set day view
        String eventDay = String.valueOf(list.get(position).getDay());
        Log.d(TAG, "getView: " + eventDay);
        tvDate.setText(eventDay);

        //set month view
        String eventMonth = String.valueOf(list.get(position).getMonth());
        Log.d(TAG, "getView: " + eventMonth);
        String monthAndYear = "";
        switch (eventMonth) {
            case "01": {
                monthAndYear = "January";
                break;
            }

            case "02": {
                monthAndYear = "February";
                break;
            }

            case "03": {
                monthAndYear = "March";
                break;
            }

            case "04": {
                monthAndYear = "April";
                break;
            }

            case "05": {
                monthAndYear = "May";
                break;
            }

            case "06": {
                monthAndYear = "June";
                break;
            }

            case "07": {
                monthAndYear = "July";
                break;
            }

            case "08": {
                monthAndYear = "August";
                break;
            }

            case "09": {
                monthAndYear = "September";
                break;
            }

            case "10": {
                monthAndYear = "October";
                break;
            }

            case "11": {
                monthAndYear = "November";
                break;
            }

            case "12": {
                monthAndYear = "December";
                break;
            }
        }

        //set day of week view
        tvDay.setText(list.get(position).getDayOfWeek());

        //set category
        ReadDataUtils.getInstance().setImageResource(ivReason, list.get(position).getChonNhom());

        tvReason.setText(list.get(position).getChonNhom());
        tvMonthAndYear.setText(monthAndYear + ", " + list.get(position).getYear());

        tvMoneyOn.setText(String.valueOf(list.get(position).getMoney()));
        if (list.get(position).isIncome()){
            tvMoneyOn.setTextColor(Color.parseColor("#008f24"));
        } else {
            tvMoneyOn.setTextColor(Color.parseColor("#dc000d"));
        }
        return convertView;
    }
}
