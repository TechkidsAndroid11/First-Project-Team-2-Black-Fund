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
    private TextView etDate;
    private int month, year, day,dayOfWeek;
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
    AlertDialog.Builder dialogBuilder;
    AlertDialog alertDialog;
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
        if (addMoney){
            tvActName.setText("INCOMES");
        } else {
            tvActName.setText("EXPENSES");
        }
        getCalendar();

    }

    private void getCalendar() {
        String textDayOfWeek = "";
        day = calendar.get(Calendar.DAY_OF_MONTH);
        month = calendar.get(Calendar.MONTH);
        year = calendar.get(Calendar.YEAR);
        dayOfWeek= calendar.get(Calendar.DAY_OF_WEEK);
//        month = month + 1;
        etDate.setText(dayOfWeek +" , "+day + "/" + month + "/" + year);}
//            case 1:{
//                textDayOfWeek = "Sunday";
//                break;
//            }
//
//            case 2:{
//                textDayOfWeek = "Monday";
//                break;
//            }
//
//            case 3:{
//                textDayOfWeek = "Tuesday";
//                break;
//            }
//
//            case 4:{
//                textDayOfWeek = "Wednesday";
//                break;
//            }
//
//            case 5:{
//                textDayOfWeek = "Thursday";
//                break;
//            }
//
//            case 6:{
//                textDayOfWeek = "Friday";
//                break;
//            }
//
//            case 7:{
//                textDayOfWeek = "Saturday";
//                break;
//            }
//        }
//        etDate.setText(textDayOfWeek  +", "+day + "/" + month + "/" + year);
//    }

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


        //chon ngay
        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(ThemGhiChu.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthofyear, int dayofmonth) {
                        monthofyear = monthofyear + 1;
                        Calendar calendar = Calendar.getInstance();
                        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
                        Date d = new Date(year, monthofyear, dayofmonth);
                        String dayOfTheWeek = sdf.format(d);
                        etDate.setText(dayOfTheWeek+" , "+ dayofmonth+"/"+ monthofyear+"/"+ year);
                    }
                }, year, month, day );
                datePickerDialog.show();
            }
        });
    }

    private void setupUI() {
        tvActName = findViewById(R.id.tv_add_transaction);
        etGhiChu = (TextView) findViewById(R.id.et_ghiChu);
        tvChonNhom = (TextView) findViewById(R.id.tv_chonNhom);
        etTien = (EditText) findViewById(R.id.et_tien);
        ivLuu = (ImageView) findViewById(R.id.iv_luu);
        ivBack = (ImageView) findViewById(R.id.iv_back);
        ivChonNhom = (ImageView) findViewById(R.id.iv_category_logo);
        etDate = (TextView) findViewById(R.id.et_date);
        calendar = Calendar.getInstance();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_luu: {
                //save income
                if (addMoney) {
                    String ghichu = etGhiChu.getText().toString();
                    String tien = etTien.getText().toString();
                    String date = etDate.getText().toString();
                    String chonNhom = tvChonNhom.getText().toString();
                    GhiChu note = new GhiChu(ghichu, tien, date, chonNhom);

                    BlackFundDatabase.getInstance(this).addGhiChu(note, true);
                    ThemGhiChu.this.finish();
                    break;
                }

                //save expenses
                else {
                    String ghichu = etGhiChu.getText().toString();
                    String tien = etTien.getText().toString();
                    String date = etDate.getText().toString();
                    String chonNhom = tvChonNhom.getText().toString();
                    GhiChu note = new GhiChu(ghichu, tien, date, chonNhom);

                    BlackFundDatabase.getInstance(this).addGhiChu(note, false);
                    ThemGhiChu.this.finish();
                    break;
                }
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


    public void updateCategory(int drawable, String name) {
        ivChonNhom.setImageResource(drawable);
        tvChonNhom.setText(name);
        alertDialog.dismiss();
    }
}