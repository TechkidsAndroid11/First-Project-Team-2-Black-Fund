package com.example.admins.blackfund.activities;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;
import android.widget.DatePicker;

import com.example.admins.blackfund.R;
import com.example.admins.blackfund.databases.BlackFundDatabase;
import com.example.admins.blackfund.models.GhiChu;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.Calendar;

public class ThemGhiChu extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = ThemGhiChu.class.toString();
    private TextView tvDate;
    private int currentMonth, currentYear, currentDay,currentDayOfWeek;
    private Calendar calendar;
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
    private int addMoney;
    private GhiChu editGhiChu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_giao_dich);
        setupUI();
        addListeners();
        loadData();
    }

    private void loadData() {
        addMoney = getIntent().getIntExtra(MainActivity.KEY, 0);
        if (addMoney == 1){
            tvActName.setText("INCOMES");
            getCalendar();
        }
        if (addMoney == 2){
            tvActName.setText("EXPENSES");
            getCalendar();
        }

        if (addMoney == 3){
            tvActName.setText("EDIT");
            editGhiChu = (GhiChu) getIntent().getSerializableExtra(MainActivity.KEY_EDIT);
            tvChonNhom.setText(editGhiChu.getChonNhom());
            tvDate.setText(editGhiChu.getDate());
            etTien.setText(editGhiChu.getMoney());
            etGhiChu.setText((editGhiChu.getGhiChu()));
            switch (editGhiChu.getChonNhom()) {
                case "FOODS": {
                    ivChonNhom.setImageResource(R.drawable.food);
                    break;
                }
                case "FRIENDS": {
                    ivChonNhom.setImageResource(R.drawable.banbe);
                    break;
                }

                case "SHOPPING": {
                    ivChonNhom.setImageResource(R.drawable.shopping);
                    break;
                }

                case "ENTERTAINMENT": {
                    ivChonNhom.setImageResource(R.drawable.giaitri);
                    break;
                }

                case "TRANSPORTATION": {
                    ivChonNhom.setImageResource(R.drawable.phuongtien);
                    break;
                }

                case "LOVE": {
                    ivChonNhom.setImageResource(R.drawable.love);
                    break;
                }

                case "WORKS": {
                    ivChonNhom.setImageResource(R.drawable.works);
                    break;
                }

                case "OTHERS": {
                    ivChonNhom.setImageResource(R.drawable.others);
                    break;
                }
            }
        }


    }

    private void getCalendar() {
        currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        currentMonth = calendar.get(Calendar.MONTH);
        currentYear = calendar.get(Calendar.YEAR);
        currentDayOfWeek= calendar.get(Calendar.DAY_OF_WEEK);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE, dd/MM/yyyy");
        String currentDateTime = simpleDateFormat.format(new Date(currentYear - 1900, currentMonth, currentDay));
        Log.d(TAG, "getCalendar: " + currentDay + " " + currentMonth + " " + currentYear + " " + currentDayOfWeek);
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
        calendar = Calendar.getInstance();
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

    private void addIncome(int addMoney) {
        String ghichu = etGhiChu.getText().toString();
        String tien = etTien.getText().toString();
        String date = tvDate.getText().toString();
        String chonNhom = tvChonNhom.getText().toString();
        GhiChu note = new GhiChu(ghichu, tien, date, chonNhom);
        if (addMoney == 1) {
            BlackFundDatabase.getInstance(this).addGhiChu(note, true);
        }
        if (addMoney == 2){
            BlackFundDatabase.getInstance(this).addGhiChu(note, false);
        }
        if (addMoney == 3){
            BlackFundDatabase.getInstance(this).updateNote(note, editGhiChu.getId());
        }
        ThemGhiChu.this.finish();
    }

    private void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE, dd/MM/yyyy");
                        String formattedDate = simpleDateFormat.format(new Date(year - 1900, month, dayOfMonth));
                        Log.d(TAG, "onDateSet: currentYear" + year);
                        Log.d(TAG, "onDateSet: " + formattedDate);
                        tvDate.setText(formattedDate);
                    }
                },
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