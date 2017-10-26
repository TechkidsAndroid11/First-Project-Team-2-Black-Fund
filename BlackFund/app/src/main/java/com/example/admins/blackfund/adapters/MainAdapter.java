package com.example.admins.blackfund.adapters;

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
    private List<GhiChu> ghiChuList;

    public MainAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<GhiChu> objects) {

        super(context, resource, objects);
        list = new ArrayList<>();
        this.context = context;
        this.resource = resource;
        this.ghiChuList = objects;
        getList();
    }
    public void getList(){
        if(ghiChuList.size() !=0 ) {
            list.add(ghiChuList.get(ghiChuList.size() - 1));
            if (ghiChuList.size() != 1){
                list.add(ghiChuList.get(ghiChuList.size() -2));
            }
        }
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        convertView = layoutInflater.inflate(resource, parent, false);
        TextView tvDate = convertView.findViewById(R.id.tv_date);
        TextView tvDay = convertView.findViewById(R.id.tv_day);
        TextView tvMonthAndYear = convertView.findViewById(R.id.tv_month_and_year);
        ImageView ivReason = convertView.findViewById((R.id.iv_reason));
        TextView tvMoneyOn = convertView.findViewById(R.id.tv_money_on);
        TextView tvMoneyUnder = convertView.findViewById(R.id.tv_money_under);
//        tvDate.setText(list.get(position).getDate());
//        tvDay.setText(list.get(position).getDate());
//        tvMonthAndYear.setText(list.get(position).getDate());
        tvMoneyOn.setText(list.get(position).getMoney());
        tvMoneyUnder.setText(list.get(position).getMoney());
        return convertView;
    }
}
