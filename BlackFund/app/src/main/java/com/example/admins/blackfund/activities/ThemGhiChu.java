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

import java.util.Calendar;

public class ThemGhiChu extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = ThemGhiChu.class.toString();
    private EditText etDate;
    int month, year, day;
    Calendar calendar;

    private EditText etGhiChu;
    private EditText etTien;
    private TextView etchonNhom;
    private TextView tvLuu;
    private ImageView ivBack;
    private ImageView ivChonNhom;
    GhiChu ghichu;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_giao_dich);

        etDate = (EditText) findViewById(R.id.et_date);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            calendar = Calendar.getInstance();
            setupUI();
            addListner();

            day = calendar.get(Calendar.DAY_OF_MONTH);
            month = calendar.get(Calendar.MONTH);
            year = calendar.get(Calendar.YEAR);
            month = month + 1;
            etDate.setText(day + "/" + month + "/" + year);
            etDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DatePickerDialog datePickerDialog = new DatePickerDialog(ThemGhiChu.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int year, int monthofyear, int dayofmonth) {
                            monthofyear = monthofyear + 1;
                            etDate.setText(dayofmonth + "/" + monthofyear + "/" + year);

                        }
                    }, year, month, day);
                    datePickerDialog.show();


                }
            });
//        }
    }

    private void addListner() {
        etchonNhom.setOnClickListener(new View.OnClickListener() {
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
        tvLuu.setOnClickListener(this);

    }

    private void setupUI() {
        etGhiChu = (EditText) findViewById(R.id.et_ghiChu);
        etchonNhom = (TextView) findViewById(R.id.et_chonNhom);
        etTien = (EditText) findViewById(R.id.et_tien);
        tvLuu = (TextView) findViewById(R.id.tv_luu);
        ivBack = (ImageView) findViewById(R.id.iv_back);
        ivChonNhom = (ImageView) findViewById(R.id.iv_chonNhom);


    }

    @Override
    public void onClick(View view) {
        Log.d(TAG, "onClick: ");
        switch (view.getId()) {
            case R.id.tv_luu: {
                Log.d(TAG, "onClick: ");
                String ghichu = etGhiChu.getText().toString();
                String tien = etTien.getText().toString();
                String date = etDate.getText().toString();
                String chonNhom = etchonNhom.getText().toString();
//                if (TextUtils.isEmpty(tien)) {
//                    etTien.setError("Cannot be empty");
//                } else if (
//                        TextUtils.isEmpty(date)) {
//                    etDate.setError(" cannot be empty");
//
//                } else {
                    GhiChu note = new GhiChu(ghichu, tien, date, chonNhom);

                    BlackFundDatabase.getInstance(this).addGhiChu(note);
                    ThemGhiChu.this.finish();

//                }


                break;
            }
            case R.id.iv_back: {
                finish();

                break;
            }


            case R.id.iv_viec: {
                updateCategory(R.drawable.works, "Công Việc");
                Log.d(TAG, "onClick: ");
                break;
            }
            case R.id.iv_food: {
                updateCategory(R.drawable.food, "Ăn uống");
                break;
            }
            case R.id.iv_friend: {
                updateCategory(R.drawable.banbe, "Bạn Bè");
                break;
            }
            case R.id.iv_love: {
                updateCategory(R.drawable.love, "Love");
                break;
            }
            case R.id.iv_shopping: {
                updateCategory(R.drawable.shopping, "Mua Sắm");
                break;
            }
            case R.id.iv_giaiTri: {
                updateCategory(R.drawable.giaitri, "Giải Trí");
                break;
            }
            case R.id.iv_others: {
                updateCategory(R.drawable.others, "Khác");
                break;
            }
            case R.id.iv_phuongTien: {
                updateCategory(R.drawable.phuongtien, "Phương Tiện");
                break;
            }
            case R.id.iv_sinhHoat: {
                updateCategory(R.drawable.sinhhoat, "Sinh Hoạt");
                break;
            }
        }
    }

    public void updateCategory(int drawable, String name) {
        ivChonNhom.setImageResource(drawable);
        etchonNhom.setText(name);
        alertDialog.dismiss();
    }
}