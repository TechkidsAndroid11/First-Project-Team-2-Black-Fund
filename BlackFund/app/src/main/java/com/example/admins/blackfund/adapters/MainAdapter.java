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

        tvDate.setText(list.get(position).getDate().substring(4, 6));
        String monthAndYear = "";
        switch (list.get(position).getDate().substring(7, 9)) {
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
        switch (list.get(position).getDate().charAt(0)) {
            case '7': {
                tvDay.setText("Thứ bảy");
                break;
            }
            case '6': {
                tvDay.setText("Thứ sáu");
                break;
            }
            case '5': {
                tvDay.setText("Thứ năm");
                break;
            }
            case '4': {
                tvDay.setText("Thứ tư");
                break;
            }
            case '3': {
                tvDay.setText("Thứ ba");
                break;
            }
            case '2': {
                tvDay.setText("Thứ hai");
                break;
            }
            case '1': {
                tvDay.setText("Chủ nhật");
            }
        }

        switch (list.get(position).getChonNhom()) {
            case "FOODS": {
                ivReason.setBackgroundResource(R.drawable.food);
                break;
            }
            case "FRIENDS": {
                ivReason.setBackgroundResource(R.drawable.banbe);
                break;
            }

            case "SHOPPING": {
                ivReason.setBackgroundResource(R.drawable.shopping);
                break;
            }

            case "ENTERTAINMENT": {
                ivReason.setBackgroundResource(R.drawable.giaitri);
                break;
            }

            case "TRANSPORTATION": {
                ivReason.setBackgroundResource(R.drawable.phuongtien);
                break;
            }

            case "LOVE": {
                ivReason.setBackgroundResource(R.drawable.love);
                break;
            }

            case "WORKS": {
                ivReason.setBackgroundResource(R.drawable.works);
                break;
            }

            case "OTHERS": {
                ivReason.setBackgroundResource(R.drawable.others);
                break;
            }
        }
        tvReason.setText(list.get(position).getChonNhom());
        tvMonthAndYear.setText(monthAndYear + ", " + list.get(position).getDate().substring(10));
        tvMoneyOn.setText(list.get(position).getMoney());
        tvMoneyUnder.setText(list.get(position).getMoney());
        return convertView;
    }
}
