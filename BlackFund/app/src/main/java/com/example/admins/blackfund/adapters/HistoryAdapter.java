package com.example.admins.blackfund.adapters;

import android.content.Context;
import android.graphics.Color;
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
import com.example.admins.blackfund.activities.HistoryActivity;
import com.example.admins.blackfund.models.GhiChu;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admins on 11/2/2017.
 */

public class HistoryAdapter extends ArrayAdapter<GhiChu> {
    private static final String TAG = HistoryActivity.class.toString();
    private List<GhiChu> list=new ArrayList<>();

    private Context context;
    private int resource;

    public HistoryAdapter(@NonNull Context context, int resource, @NonNull List<GhiChu> objects) {
        super(context, resource, objects);
        Log.d(TAG, "HistoryAdapter: "+ objects.toString());
        this.context = context;
        this.resource = resource;
        this.list = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        convertView = layoutInflater.inflate(resource, parent, false);
        ImageView ivMoney = convertView.findViewById(R.id.iv_image1);
        TextView tvReason = convertView.findViewById(R.id.tv_reason1);
        TextView tvMoney1 = convertView.findViewById(R.id.tv_money1);
        TextView tvNote1 = convertView.findViewById(R.id.tv_note1);


        tvMoney1.setText(list.get(position).getMoney()+"");
        tvNote1.setText(list.get(position).getGhiChu());
        switch (list.get(position).getChonNhom()) {
            case "FOODS": {
                ivMoney.setImageResource(R.drawable.food);
                break;
            }
            case "FRIENDS": {
                ivMoney.setImageResource(R.drawable.banbe);
                break;
            }

            case "SHOPPING": {
                ivMoney.setImageResource(R.drawable.shopping);
                break;
            }

            case "ENTERTAINMENT": {
                ivMoney.setImageResource(R.drawable.giaitri);
                break;
            }

            case "TRANSPORTATION": {
                ivMoney.setImageResource(R.drawable.phuongtien);
                break;
            }

            case "LOVE": {
                ivMoney.setImageResource(R.drawable.love);
                break;
            }

            case "WORKS": {
                ivMoney.setImageResource(R.drawable.works);
                break;
            }

            case "OTHERS": {
                ivMoney.setImageResource(R.drawable.others);
                break;
            }
            case "sub EXPENSES": {
                ivMoney.setImageResource(R.drawable.sinhhoat);
            }
        }
        tvReason.setText(list.get(position).getChonNhom());
        if (list.get(position).isIncome()) {
            tvMoney1.setTextColor(Color.parseColor("#008f24"));
        } else {
            tvMoney1.setTextColor(Color.parseColor("#dc000d"));
        }
        Log.d(TAG, "getView: "+list.toString());

        return convertView;

    }
}
