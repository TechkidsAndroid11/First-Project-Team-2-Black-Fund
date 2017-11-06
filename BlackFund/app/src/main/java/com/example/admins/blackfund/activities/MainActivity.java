package com.example.admins.blackfund.activities;

import android.content.Context;
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
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;



import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private ViewPager vpOverview;
    private ListView lvHistory;
    private TextView tvMoney;
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
    private FloatingActionsMenu fbActionMenu;
    private FloatingActionButton btAddIncome;
    private FloatingActionButton btAddExpense;
    private FloatingActionButton btHistory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        checkPassword();
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
        btAddIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ThemGhiChu.class);
                intent.putExtra(KEY_ISINCOME, true);
                intent.putExtra(EDIT_MODE, false);
                startActivity(intent);
                fbActionMenu.collapse();
            }
        });

        btAddExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ThemGhiChu.class);
                intent.putExtra(KEY_ISINCOME, false);
                intent.putExtra(EDIT_MODE, false);
                startActivity(intent);
                fbActionMenu.collapse();
            }
        });

        btHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, HistoryActivity.class));
            }
        });

        lvHistory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, ThemGhiChu.class);
                intent.putExtra(EDIT_MODE, true);
                intent.putExtra(KEY_EDIT, BlackFundDatabase.getInstance(MainActivity.this).getListGhiChu().get(position));
                startActivity(intent);
                fbActionMenu.collapse();
            }
        });
    }


    private void setupUI() {
        lvHistory = (ListView) findViewById(R.id.lv_recent_history);
        vpOverview = (ViewPager) findViewById(R.id.vp_overview);
        vpOverview.setAdapter(new OverviewPagerAdapter(getSupportFragmentManager()));
        tvMoney = (TextView) findViewById(R.id.tv_money);
        fbActionMenu = findViewById(R.id.fb_add_button);
        setupActionMenu();
    }

    private void setupActionMenu() {
        btAddIncome = new FloatingActionButton(this);
        btAddIncome.setTitle("Income");
        btAddIncome.setIcon(R.drawable.ic_add_black_24dp);
        btAddIncome.setSize(FloatingActionButton.SIZE_MINI);
        btAddIncome.setColorNormalResId(R.color.colorPrimary);
        btAddIncome.setColorPressedResId(R.color.colorPrimaryDark);
        fbActionMenu.addButton(btAddIncome);

        btAddExpense = new FloatingActionButton(this);
        btAddExpense.setTitle("Expense");
        btAddExpense.setIcon(R.drawable.ic_remove_black_24dp);
        btAddExpense.setSize(FloatingActionButton.SIZE_MINI);
        btAddExpense.setColorNormalResId(R.color.colorPrimary);
        btAddExpense.setColorPressedResId(R.color.colorPrimaryDark);
        fbActionMenu.addButton(btAddExpense);

        btHistory = new FloatingActionButton(this);
        btHistory.setTitle("History");
        btHistory.setIcon(R.drawable.ic_history_black_24dp);
        btHistory.setSize(FloatingActionButton.SIZE_MINI);
        btHistory.setColorNormalResId(R.color.colorPrimary);
        btHistory.setColorPressedResId(R.color.colorPrimaryDark);
        fbActionMenu.addButton(btHistory);
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
        String strMoneyBalance = sharedPreferences.getString(MONEY_BALANCE, "");
        if (strMoneyBalance == "") {
//            onFirstTime();
        } else {
            int intMoneyBalance = Integer.parseInt(strMoneyBalance);
            int newMoneyBalance = intMoneyBalance + totalIncome - totalExpense;
            if (newMoneyBalance <= 0) {
                newMoneyBalance = 0;
            }
            tvMoney.setText(NumberFormat.getNumberInstance(Locale.US).format(newMoneyBalance));
        }
    }

    //TODO main activity overview fragment
    //TODO: adjust balance
}