package com.example.admins.blackfund.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admins.blackfund.R;
import com.example.admins.blackfund.models.GhiChu;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nguyen Duc Anh on 10/25/2017.
 */

public class MainAdapter extends ArrayAdapter<GhiChu> {
    private List<GhiChu> list;
    private Context context;
    private int resource;


    public MainAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<GhiChu> objects) {

        super(context, resource, objects);
        list = new ArrayList<>();
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
        TextView tvMoneyUnder = convertView.findViewById(R.id.tv_money_under);

        tvDate.setText(list.get(position).getDate().substring(list.get(position).getDate().lastIndexOf(" ") + 1 , list.get(position).getDate().lastIndexOf(" ") + 3));
        String monthAndYear = "";
        switch (list.get(position).getDate().substring(list.get(position).getDate().lastIndexOf(" ") + 4, list.get(position).getDate().lastIndexOf("/"))) {
            case "1": {
                monthAndYear = "January";
                break;
            }

            case "2": {
                monthAndYear = "February";
                break;
            }

            case "3": {
                monthAndYear = "March";
                break;
            }

            case "4": {
                monthAndYear = "April";
                break;
            }

            case "5": {
                monthAndYear = "May";
                break;
            }

            case "6": {
                monthAndYear = "June";
                break;
            }

            case "7": {
                monthAndYear = "July";
                break;
            }

            case "8": {
                monthAndYear = "August";
                break;
            }

            case "9": {
                monthAndYear = "September";
                break;
            }

            case "10": {
                monthAndYear = "Octorber";
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

        tvDay.setText(list.get(position).getDate().substring(0, list.get(position).getDate().lastIndexOf(",")));
        switch (list.get(position).getChonNhom()) {
            case "FOODS": {
                ivReason.setImageResource(R.drawable.food);
                break;
            }
            case "FRIENDS": {
                ivReason.setImageResource(R.drawable.banbe);
                break;
            }

            case "SHOPPING": {
                ivReason.setImageResource(R.drawable.shopping);
                break;
            }

            case "ENTERTAINMENT": {
                ivReason.setImageResource(R.drawable.giaitri);
                break;
            }

            case "TRANSPORTATION": {
                ivReason.setImageResource(R.drawable.phuongtien);
                break;
            }

            case "LOVE": {
                ivReason.setImageResource(R.drawable.love);
                break;
            }

            case "WORKS": {
                ivReason.setImageResource(R.drawable.works);
                break;
            }

            case "OTHERS": {
                ivReason.setImageResource(R.drawable.others);
                break;
            }
        }
        tvReason.setText(list.get(position).getChonNhom());
        tvMonthAndYear.setText(monthAndYear + ", " + list.get(position).getDate().substring(list.get(position).getDate().lastIndexOf("/") + 1));
        //tvMonthAndYear.setText(list.get(position).getDate().substring(list.get(position).getDate().lastIndexOf(",") + 1));
        tvMoneyOn.setText(list.get(position).getMoney());
        tvMoneyUnder.setText(list.get(position).getMoney());
        return convertView;
    }
}
