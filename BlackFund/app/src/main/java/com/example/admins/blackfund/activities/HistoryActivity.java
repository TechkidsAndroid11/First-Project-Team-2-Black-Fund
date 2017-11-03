package com.example.admins.blackfund.activities;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.admins.blackfund.R;
import com.example.admins.blackfund.adapters.HistoryAdapter;
import com.example.admins.blackfund.databases.BlackFundDatabase;
import com.example.admins.blackfund.models.GhiChu;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.ArrayList;
import java.util.Calendar;

public class HistoryActivity extends AppCompatActivity {
    private ListView lvCalendar;
    private static final String TAG = HistoryAdapter.class.toString();
    ArrayList<GhiChu> listHistory = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);


        final MaterialCalendarView materialCalendarView = findViewById(R.id.calendarView);

        materialCalendarView.state().edit()
                .setFirstDayOfWeek(Calendar.MONDAY)
                .setMinimumDate(CalendarDay.from(1900, 1, 1))
                .setMaximumDate(CalendarDay.from(2100, 12, 31))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();

        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
//                listHistory= BlackFundDatabase.getInstance(HistoryActivity.this).getListHistorynote(date.toString().substring(12,date.toString().length()-1));

                int day = date.getDay();
                String day1;
                if (day < 10) {
                    day1 = "0" + day;

                } else {
                    day1 = "" + day;
                }
                int month = date.getMonth() + 1;
                int year = date.getYear();
                String date1 = year + "-" + month + "-" + day1;

                Log.d(TAG, "onDateSelected: " + listHistory.size());
                Log.d(TAG, "onDateSelected: " + date1.toString());
//                String day= date.toString().substring(20,date.toString().length()-1);
//                String month=date.toString().substring(17,date.toString().length()-3);
//
//
//                int monthV= Integer.parseInt(month.replaceAll("[\\D]", ""));
//                String year= date.toString().substring(12,date.toString().length()-6);
//                int month1= monthV+1;
//                String date1 =year+"-"+month1+"-"+day;
                Log.d(TAG, "onDateSelected: ");
                listHistory = BlackFundDatabase.getInstance(HistoryActivity.this).getListHistorynote(date1.toString());

                HistoryAdapter historyAdapter = new HistoryAdapter(HistoryActivity.this, R.layout.item_history_activity, listHistory);
                lvCalendar = findViewById(R.id.lv_calendar);
                lvCalendar.setAdapter(historyAdapter);

            }
        });


    }
}
