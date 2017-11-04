package com.example.admins.blackfund.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.admins.blackfund.activities.lockviewactivities.CreatPasswordActivity;
import com.example.admins.blackfund.activities.lockviewactivities.InputPasswordActivity;
import com.example.admins.blackfund.R;
import com.example.admins.blackfund.adapters.MainAdapter;
import com.example.admins.blackfund.adapters.OverviewPagerAdapter;
import com.example.admins.blackfund.databases.BlackFundDatabase;
import com.example.admins.blackfund.models.GhiChu;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ImageView ivPlus;
    private ViewPager vpOverview;
    private ListView lvHistory;
    private TextView tvMoney;
    private ImageView ivMinus;
    public static String KEY_ISINCOME = "KEY_ISINCOME";
    public static String KEY_EDIT = "KEY_EDIT";
    public static String EDIT_MODE = "EDIT_MODE";
    public static String SHARED_PREFERENCES = "my_data";
    public static String MONEY_BALANCE = "value";
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog alertDialog;
    private EditText etValue;
    private Button btOK;
    public static boolean checkPass = true;
    private ImageView ivMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkPassword();
        setupUI();
        addListeners();
        onFirstTime();
    }

    private void checkPassword() {
        if (checkPass) {
            SharedPreferences msharedPreferences = getSharedPreferences("PRESS", 0);
            String password = msharedPreferences.getString("password", "0");
            if (password.equals("0")) {
                Intent intent = new Intent(getApplicationContext(), CreatPasswordActivity.class);
                startActivity(intent);
                finish();
            } else {
                Intent intent = new Intent(getApplicationContext(), InputPasswordActivity.class);
                startActivity(intent);
                finish();
            }
        }
    }

    private void onFirstTime() {
        dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater layoutInflater = this.getLayoutInflater();
        View dialogView = layoutInflater.inflate(R.layout.sh_item, null);
        dialogBuilder.setView(dialogView);
        alertDialog = dialogBuilder.create();

        final SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);
        final boolean checkFirstTime = sharedPreferences.getBoolean("firstTime", true);
        String beginningValue = sharedPreferences.getString("value", "");
        tvMoney.setText(beginningValue);
        if (checkFirstTime) {
            alertDialog.show();
            etValue = dialogView.findViewById(R.id.et_value);
            btOK = dialogView.findViewById(R.id.bt_ok);

            btOK.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String beginningValue = etValue.getText().toString();
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(MONEY_BALANCE, beginningValue);
                    editor.putBoolean("firstTime", false);
                    editor.commit();
                    tvMoney.setText(beginningValue);
                    alertDialog.dismiss();
                }
            });
        }
    }

    private void addListeners() {
        ivPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ThemGhiChu.class);
                intent.putExtra(KEY_ISINCOME, true);
                intent.putExtra(EDIT_MODE, false);
                startActivity(intent);
            }
        });

        ivMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ThemGhiChu.class);
                intent.putExtra(KEY_ISINCOME, false);
                intent.putExtra(EDIT_MODE, false);
                startActivity(intent);
            }
        });

        lvHistory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, ThemGhiChu.class);
                intent.putExtra(EDIT_MODE, true);
                intent.putExtra(KEY_EDIT, BlackFundDatabase.getInstance(MainActivity.this).getListGhiChu().get(position));
                startActivity(intent);
            }
        });
        ivMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopUp();
            }
        });
    }


    private void setupUI() {
        lvHistory = (ListView) findViewById(R.id.lv_recent_history);
        vpOverview = (ViewPager) findViewById(R.id.vp_overview);
        vpOverview.setAdapter(new OverviewPagerAdapter(getSupportFragmentManager()));
        tvMoney = (TextView) findViewById(R.id.tv_money);
        ivPlus = (ImageView) findViewById(R.id.iv_incomes);
        ivMinus = (ImageView) findViewById(R.id.iv_expenses);
        ivMenu= findViewById(R.id.iv_menu);

    }

    @Override
    protected void onStart() {
        super.onStart();
        List<GhiChu> ghiChuList = BlackFundDatabase.getInstance(this).getListGhiChu();
        lvHistory.setAdapter(new MainAdapter(this, R.layout.an_item, ghiChuList));
        calculateBalance();
    }

    private void calculateBalance() {
        int totalIncome = BlackFundDatabase.getInstance(this).calculateIncome();
        int totalExpense = BlackFundDatabase.getInstance(this).calculateExpense();
        final SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);
        final int moneyBalance = Integer.parseInt(sharedPreferences.getString(MONEY_BALANCE, ""));
        int newMoneyBalance = moneyBalance + totalIncome - totalExpense;
        if (newMoneyBalance <= 0){
            newMoneyBalance = 0;
        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(MONEY_BALANCE, String.valueOf(moneyBalance));
        editor.commit();
        tvMoney.setText(String.valueOf(newMoneyBalance));
    }

    public void showPopUp(){
        final PopupMenu popupMenu = new PopupMenu(MainActivity.this, ivMenu);
        popupMenu.getMenuInflater().inflate(R.menu.menu_trans, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.menu_overview:{
//                        startActivity(new Intent(HistoryActivity.class, MainActivity.class));
                        popupMenu.dismiss();
                        break;
                    }

                    case R.id.menu_history:{
                        startActivity(new Intent(MainActivity.this, HistoryActivity.class));
                        break;
                    }

                    default: break;
                }
                return false;
            }
        });
        popupMenu.show();
    }

    //TODO: adjust balance
}