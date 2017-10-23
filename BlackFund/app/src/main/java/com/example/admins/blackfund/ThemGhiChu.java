package com.example.admins.blackfund;

import android.app.DatePickerDialog;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.widget.DatePicker;

public class ThemGhiChu extends AppCompatActivity {
    private EditText etDate;
    int month,year,day;
    Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_giao_dich);

        etDate= (EditText) findViewById(R.id.et_date);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            calendar= Calendar.getInstance();

            day= calendar.get(Calendar.DAY_OF_MONTH);
            month=calendar.get(Calendar.MONTH);
            year=calendar.get(Calendar.YEAR);
            month=month+1;
            etDate.setText(day+"/"+month+"/"+year);
            etDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DatePickerDialog datePickerDialog= new DatePickerDialog(ThemGhiChu.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int year, int monthofyear, int dayofmonth) {
                            monthofyear=monthofyear+1;
                            etDate.setText(dayofmonth+"/"+monthofyear+"/"+year);

                        }
                    },year,month,day);
                datePickerDialog.show();


    }
});}
        }}