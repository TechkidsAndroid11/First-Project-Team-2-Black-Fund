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
import com.example.admins.blackfund.utils.ReadDataUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admins on 11/2/2017.
 */

public class HistoryAdapter extends ArrayAdapter<GhiChu> {
    private static final String TAG = HistoryActivity.class.toString();
    private List<GhiChu> list = new ArrayList<>();
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
        ImageView ivCategoryImage = convertView.findViewById(R.id.iv_category_image);
        TextView tvReason = convertView.findViewById(R.id.tv_category);
        TextView tvMoney = convertView.findViewById(R.id.tv_money);
        TextView tvNote = convertView.findViewById(R.id.tv_note);


        tvMoney.setText(list.get(position).getMoney());
        tvNote.setText(list.get(position).getGhiChu());
        ReadDataUtils.getInstance().setImageResource(ivCategoryImage, list.get(position).getChonNhom());
        tvReason.setText(list.get(position).getChonNhom());
        if (list.get(position).isIncome()) {
            tvMoney.setTextColor(Color.parseColor("#008f24"));
        } else {
            tvMoney.setTextColor(Color.parseColor("#dc000d"));
        }
        Log.d(TAG, "getView: "+list.toString());

        return convertView;

    }
}
