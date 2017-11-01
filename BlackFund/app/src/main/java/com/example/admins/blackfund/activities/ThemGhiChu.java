package com.example.admins.blackfund.activities;

import android.app.DatePickerDialog;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;
import android.widget.DatePicker;

import com.example.admins.blackfund.R;
import com.example.admins.blackfund.databases.BlackFundDatabase;
import com.example.admins.blackfund.models.GhiChu;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ThemGhiChu extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = ThemGhiChu.class.toString();
    private TextView tvDate;
    private int currentMonth, currentYear, currentDay, currentDayOfWeek;
    private Calendar currentCalendar;
    private TextView tvActName;
    private TextView etGhiChu;
    private EditText etTien;
    private TextView tvChonNhom;
    private ImageView ivLuu;
    private ImageView ivBack;
    private ImageView ivChonNhom;
    private ImageView ivShopping;
    private ImageView ivGiaiTri;
    private ImageView ivBanbe;
    private ImageView ivOthers;
    private ImageView ivSinhHoat;
    private ImageView ivPhuongTien;
    private ImageView ivLove;
    private ImageView ivCongViec;
    private ImageView ivAnUong;
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog alertDialog;
    private boolean addMoney;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_giao_dich);
        setupUI();
        addListeners();
        loadData();
    }

    private void loadData() {
        addMoney = getIntent().getBooleanExtra(MainActivity.KEY, true);
        if (addMoney) {
            tvActName.setText("INCOMES");
        } else {
            tvActName.setText("EXPENSES");
        }
        getCalendar();
    }

    private void getCalendar() {
        currentDay = currentCalendar.get(Calendar.DAY_OF_MONTH);
        currentMonth = currentCalendar.get(Calendar.MONTH);
        currentYear = currentCalendar.get(Calendar.YEAR);
        currentDayOfWeek = currentCalendar.get(Calendar.DAY_OF_WEEK);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE, dd/MM/yyyy");
        String currentDateTime = simpleDateFormat.format(new Date(currentYear - 1900, currentMonth, currentDay));
        tvDate.setText(currentDateTime);
    }


    private void addListeners() {
        //chon ly do
        tvChonNhom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogBuilder = new AlertDialog.Builder(ThemGhiChu.this);
                LayoutInflater layoutInflater = ThemGhiChu.this.getLayoutInflater();
                View dialogView = layoutInflater.inflate(R.layout.list_nhom, null);
                dialogBuilder.setView(dialogView);

                ivAnUong = dialogView.findViewById(R.id.iv_food);
                ivBanbe = dialogView.findViewById(R.id.iv_friend);
                ivCongViec = dialogView.findViewById(R.id.iv_viec);
                ivGiaiTri = dialogView.findViewById(R.id.iv_giaiTri);
                ivPhuongTien = dialogView.findViewById(R.id.iv_phuongTien);
                ivLove = dialogView.findViewById(R.id.iv_love);
                ivShopping = dialogView.findViewById(R.id.iv_shopping);
                ivSinhHoat = dialogView.findViewById(R.id.iv_sinhHoat);
                ivOthers = dialogView.findViewById(R.id.iv_others);

                ivAnUong.setOnClickListener(ThemGhiChu.this);
                ivBanbe.setOnClickListener(ThemGhiChu.this);
                ivCongViec.setOnClickListener(ThemGhiChu.this);
                ivGiaiTri.setOnClickListener(ThemGhiChu.this);
                ivPhuongTien.setOnClickListener(ThemGhiChu.this);
                ivLove.setOnClickListener(ThemGhiChu.this);
                ivShopping.setOnClickListener(ThemGhiChu.this);
                ivSinhHoat.setOnClickListener(ThemGhiChu.this);
                ivOthers.setOnClickListener(ThemGhiChu.this);

                alertDialog = dialogBuilder.create();
                alertDialog.show();
            }
        });
        ivLuu.setOnClickListener(this);
        ivBack.setOnClickListener(this);
        tvDate.setOnClickListener(this);
    }

    private void setupUI() {
        tvActName = findViewById(R.id.tv_add_transaction);
        etGhiChu = (TextView) findViewById(R.id.et_ghiChu);
        tvChonNhom = (TextView) findViewById(R.id.tv_chonNhom);
        etTien = (EditText) findViewById(R.id.et_tien);
        ivLuu = (ImageView) findViewById(R.id.iv_luu);
        ivBack = (ImageView) findViewById(R.id.iv_back);
        ivChonNhom = (ImageView) findViewById(R.id.iv_category_logo);
        tvDate = (TextView) findViewById(R.id.tv_date);
        currentCalendar = Calendar.getInstance();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_luu: {
                addIncome(addMoney);
                break;
            }

            case R.id.tv_date: {
                showDatePickerDialog();
                break;
            }

            case R.id.iv_back: {
                super.onBackPressed();
                break;
            }

            case R.id.iv_viec: {
                updateCategory(R.drawable.works, "WORKS");
                break;
            }
            case R.id.iv_food: {
                updateCategory(R.drawable.food, "FOODS");
                break;
            }
            case R.id.iv_friend: {
                updateCategory(R.drawable.banbe, "FRIENDS");
                break;
            }
            case R.id.iv_love: {
                updateCategory(R.drawable.love, "LOVE");
                break;
            }
            case R.id.iv_shopping: {
                updateCategory(R.drawable.shopping, "SHOPPING");
                break;
            }
            case R.id.iv_giaiTri: {
                updateCategory(R.drawable.giaitri, "ENTERTAINMENT");
                break;
            }
            case R.id.iv_others: {
                updateCategory(R.drawable.others, "OTHERS");
                break;
            }
            case R.id.iv_phuongTien: {
                updateCategory(R.drawable.phuongtien, "TRANSPORTATION");
                break;
            }
            case R.id.iv_sinhHoat: {
                updateCategory(R.drawable.sinhhoat, "SUBEXPENSES");
                break;
            }
        }
    }

    private void addIncome(boolean addMoney) {
        String ghichu = etGhiChu.getText().toString();
        int tien = Integer.parseInt(etTien.getText().toString());

        //format date
        String date = tvDate.getText().toString();
        String subDate = date.substring(date.indexOf(", ") + 2, date.length());
        SimpleDateFormat dateParser = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date selectedDate;
        String formattedDate;
        try {
            selectedDate = dateParser.parse(subDate);
            SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
            formattedDate = dateFormatter.format(selectedDate);
            Log.d(TAG, "addIncome: " + formattedDate);
            String chonNhom = tvChonNhom.getText().toString();
            GhiChu note = new GhiChu(ghichu, tien, formattedDate , chonNhom);
            if (addMoney) {
                BlackFundDatabase.getInstance(this).addGhiChu(note, true);
            } else {
                BlackFundDatabase.getInstance(this).addGhiChu(note, false);
            }
            ThemGhiChu.this.finish();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void showDatePickerDialog() {
        DatePickerDialog.OnDateSetListener dpdOnDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE, dd/MM/yyyy");
                String formattedDate = simpleDateFormat.format(new Date(year - 1900, month, dayOfMonth));
                tvDate.setText(formattedDate);
                Log.d(TAG, "onDateSet: " + formattedDate);
            }
        };

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                dpdOnDateSetListener,
                currentYear,
                currentMonth,
                currentDay);
        datePickerDialog.show();
    }

    public void updateCategory(int drawable, String name) {
        ivChonNhom.setImageResource(drawable);
        tvChonNhom.setText(name);
        alertDialog.dismiss();
    }
}