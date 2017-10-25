package com.example.admins.blackfund.activities;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.admins.blackfund.R;
import com.example.admins.blackfund.adapters.MainAdapter;
import com.example.admins.blackfund.adapters.OverviewPagerAdapter;
import com.example.admins.blackfund.databases.BlackFundDatabase;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ImageView ivPlus;
    private ViewPager vpOverview;
    private ListView lvEvent;
    private ListView lvHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupUI();
        addListeners();
    }

    private void addListeners() {
        ivPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,ThemGhiChu.class);
                startActivity(intent);
            }
        });
    }

    private void setupUI() {
        lvHistory = (ListView) findViewById(R.id.lv_history);
        vpOverview = (ViewPager) findViewById(R.id.vp_overview);
        vpOverview.setAdapter(new OverviewPagerAdapter(getSupportFragmentManager()));

        ivPlus = (ImageView) findViewById(R.id.iv_plus);
    }

    @Override
    protected void onStart() {
        super.onStart();
        MainAdapter mainAdapter = new MainAdapter(this, R.layout.an_item, BlackFundDatabase.getInstance(this).getListGhiChu());
        lvHistory.setAdapter(mainAdapter);
    }
}
