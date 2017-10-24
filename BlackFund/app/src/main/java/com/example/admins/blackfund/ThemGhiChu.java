package com.example.admins.blackfund;

import android.app.DatePickerDialog;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.*;
import android.widget.DatePicker;

import java.util.Date;

public class ThemGhiChu extends AppCompatActivity implements View.OnClickListener {
    private EditText etDate;
    int month, year, day;
    Calendar calendar;
    private EditText etGhiChu;
    private EditText etTien;
    private EditText etchonNhom;
    private TextView tvLuu;
    private ImageView ivBack;
    GhiChu ghichu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_giao_dich);

        setupUI();
        addListeners();

        etDate = (EditText) findViewById(R.id.et_date);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            calendar = Calendar.getInstance();

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
        }
    }

    private void addListeners() {
        etchonNhom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        tvLuu.setOnClickListener(this);
    }

    private void setupUI() {
        etGhiChu = (EditText) findViewById(R.id.et_ghiChu);
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


        }
    }
}