package com.example.admins.blackfund.activities;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
<<<<<<< HEAD
=======
import android.util.Log;
import android.view.LayoutInflater;
>>>>>>> 02103c8e8c2ce485e90fa9d3b933df9c65869948
import android.view.View;
import android.widget.*;
import android.widget.DatePicker;

<<<<<<< HEAD:BlackFund/app/src/main/java/com/example/admins/blackfund/ThemGhiChu.java
import java.util.Date;

public class ThemGhiChu extends AppCompatActivity implements View.OnClickListener {
=======
import com.example.admins.blackfund.R;
import com.example.admins.blackfund.databases.BlackFundDatabase;
import com.example.admins.blackfund.models.GhiChu;

<<<<<<< HEAD
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
=======
<<<<<<< HEAD
public class ThemGhiChu extends AppCompatActivity {
>>>>>>> 6883d7eae7d2e8a8068f6c65c563ae5d680e046c:BlackFund/app/src/main/java/com/example/admins/blackfund/activities/ThemGhiChu.java
=======
>>>>>>> 6adaaa5895ebb61d23b7759d29057fd6727647f3
import java.util.Calendar;

public class ThemGhiChu extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = ThemGhiChu.class.toString();
>>>>>>> 02103c8e8c2ce485e90fa9d3b933df9c65869948
    private EditText etDate;
    int month, year, day,dayOfWeek;
    Calendar calendar;
    private EditText etGhiChu;
    private EditText etTien;
    private EditText etchonNhom;
    private TextView tvLuu;
    private ImageView ivBack;
    GhiChu ghichu;

    private TextView etGhiChu;
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
    private boolean addMoney;
    private boolean modeSP;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_giao_dich);

<<<<<<< HEAD
        setupUI();
        addListeners();

        etDate = (EditText) findViewById(R.id.et_date);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            calendar = Calendar.getInstance();
=======
        etDate = (EditText) findViewById(R.id.et_date);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
<<<<<<< HEAD
        calendar = Calendar.getInstance();
        setupUI();
        addListner();
        loadData();

        day = calendar.get(Calendar.DAY_OF_MONTH);
        month = calendar.get(Calendar.MONTH);
        year = calendar.get(Calendar.YEAR);
        dayOfWeek= calendar.get(Calendar.DAY_OF_WEEK);
        month = month + 1;
        etDate.setText(dayOfWeek +" , "+day + "/" + month + "/" + year);
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
=======
            calendar = Calendar.getInstance();
            setupUI();
            addListner();
>>>>>>> 02103c8e8c2ce485e90fa9d3b933df9c65869948

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
<<<<<<< HEAD
        }
    }

    private void addListeners() {
        etchonNhom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        tvLuu.setOnClickListener(this);
=======
>>>>>>> 6adaaa5895ebb61d23b7759d29057fd6727647f3
//        }
    }

    private void loadData() {
        addMoney = getIntent().getBooleanExtra(MainActivity.KEY, true);
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
<<<<<<< HEAD
        ivBack.setOnClickListener(this);
    }

    private void setupUI() {
        etGhiChu = (TextView) findViewById(R.id.et_ghiChu);
=======

>>>>>>> 02103c8e8c2ce485e90fa9d3b933df9c65869948
    }

    private void setupUI() {
        etGhiChu = (EditText) findViewById(R.id.et_ghiChu);
<<<<<<< HEAD
        etchonNhom = (EditText) findViewById(R.id.et_chonNhom);
        etTien = (EditText) findViewById(R.id.et_tien);
        tvLuu = (TextView) findViewById(R.id.tv_luu);
        ivBack = (ImageView) findViewById(R.id.iv_back);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_luu: {
                String ghichu = etGhiChu.getText().toString();
                String tien = etTien.getText().toString();
                String date = etDate.getText().toString();
                String chonNhom = etchonNhom.getText().toString();
                if (TextUtils.isEmpty(tien)) {
                    etTien.setError("Cannot be empty");
                } else if (
                        TextUtils.isEmpty(date)) {
                    etDate.setError(" cannot be empty");

                } else {

                }


                break;
            }
            case R.id.iv_back: {

                break;
            }
=======
>>>>>>> 6adaaa5895ebb61d23b7759d29057fd6727647f3
        etchonNhom = (TextView) findViewById(R.id.et_chonNhom);
        etTien = (EditText) findViewById(R.id.et_tien);
        tvLuu = (TextView) findViewById(R.id.tv_luu);
        ivBack = (ImageView) findViewById(R.id.iv_back);
        ivChonNhom = (ImageView) findViewById(R.id.iv_chonNhom);
>>>>>>> 02103c8e8c2ce485e90fa9d3b933df9c65869948


        }
    }
<<<<<<< HEAD
=======

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
                Log.d(TAG, "onClick: bbb"
                );
                super.onBackPressed();


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
>>>>>>> 02103c8e8c2ce485e90fa9d3b933df9c65869948
}