package com.example.admins.blackfund.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.admins.blackfund.R;
import com.example.admins.blackfund.adapters.MainAdapter;
import com.example.admins.blackfund.adapters.OverviewPagerAdapter;
import com.example.admins.blackfund.databases.BlackFundDatabase;
import com.example.admins.blackfund.models.GhiChu;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ImageView ivPlus;
    private ViewPager vpOverview;
    private ListView lvEvent;
    private ListView lvHistory;
    private TextView tvMoney;
    private boolean addMoney;
    private ImageView ivMinus;
    public static String KEY = "KEY";
    AlertDialog.Builder dialogBuilder;
    AlertDialog alertDialog;
    private EditText etValue;
    private boolean beginning;
    CheckBox checkValue;
    Button btOK;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupUI();
        addListeners();

        dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater layoutInflater = this.getLayoutInflater();
        View dialogView = layoutInflater.inflate(R.layout.sh_item, null);
        dialogBuilder.setView(dialogView);
        alertDialog = dialogBuilder.create();
        ;

        SharedPreferences sharedPreferences = getSharedPreferences("my_data", MODE_PRIVATE);
        boolean check = sharedPreferences.getBoolean("firstTime", true);
        if (check) {
            alertDialog.show();
//            String beginningValue = sharedPreferences.getString("value", etValue.getText().toString());
////            tvMoney.getText(beginningValue);

        }
//        String beginningValue = sharedPreferences.getString("value", null);
//
//        SharedPreferences.Editor editor = sharedPreferences.edit();


//            if(!check){
//                editor.clear();
//            } else
//            {
//                editor.putString("value", beginningValue);
//            }
//            editor.commit();
//        if(beginningValue!=null){
//            alertDialog.show();

    }


    private void addListeners() {


        ivPlus.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View view) {
                                          addMoney = true;
                                          Intent intent = new Intent(MainActivity.this, ThemGhiChu.class);
                                          intent.putExtra(KEY, addMoney);
                                          startActivity(intent);

                                      }
                                  }
        );
        ivMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addMoney = false;
                Intent intent = new Intent(MainActivity.this, ThemGhiChu.class);
                intent.putExtra(KEY, addMoney);
                startActivity(intent);
            }
        });
    }


    private void setupUI() {
        lvHistory = (ListView) findViewById(R.id.lv_recent_history);
        vpOverview = (ViewPager) findViewById(R.id.vp_overview);
        vpOverview.setAdapter(new OverviewPagerAdapter(getSupportFragmentManager()));
        tvMoney = (TextView) findViewById(R.id.tv_money);
        ivPlus = (ImageView) findViewById(R.id.iv_plus);
        ivMinus = (ImageView) findViewById(R.id.iv_minus);
        etValue = (EditText) findViewById(R.id.et_value);
        btOK= (Button) findViewById(R.id.bt_ok);
    }

    @Override
    protected void onStart() {
        super.onStart();
        List<GhiChu> ghiChuList = BlackFundDatabase.getInstance(this).getListGhiChu();
        MainAdapter mainAdapter = new MainAdapter(this, R.layout.an_item,
                ghiChuList.subList(ghiChuList.size() - 2, ghiChuList.size()));
        lvHistory.setAdapter(mainAdapter);
    }

}