package com.example.admins.blackfund.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;

import com.example.admins.blackfund.R;
import com.example.admins.blackfund.adapters.HistoryAdapter;
import com.example.admins.blackfund.adapters.MainAdapter;
import com.example.admins.blackfund.databases.BlackFundDatabase;
import com.example.admins.blackfund.models.GhiChu;
import com.example.admins.blackfund.utils.ReadDataUtils;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class HistoryActivity extends AppCompatActivity {
    private ListView lvCalendar;
    private static final String TAG = HistoryAdapter.class.toString();
    private ArrayList<GhiChu> listHistory = new ArrayList<>();
    private MaterialCalendarView materialCalendarView;
    private ImageView ivMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        setupUI();
        setupCalendar();
        addListerners();
    }

    private void addListerners() {
        ivMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopUp();
            }
        });
    }

    private void setupCalendar() {
        materialCalendarView.state().edit()
                .setFirstDayOfWeek(Calendar.MONDAY)
                .setMinimumDate(CalendarDay.from(1900, 1, 1))
                .setMaximumDate(CalendarDay.from(2100, 12, 31))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();

        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                int seletedDay = date.getDay();
                int selectedMonth = date.getMonth();
                int seletedYear = date.getYear();
                Date seletedDate = new Date(seletedYear - 1900, selectedMonth, seletedDay);

                Log.d(TAG, "onDateSelected: " + ReadDataUtils.getInstance().formatDatabaseDate(seletedDate));
                listHistory = BlackFundDatabase.getInstance(HistoryActivity.this).getListHistorynote(ReadDataUtils.getInstance().formatDatabaseDate(seletedDate));

                HistoryAdapter historyAdapter = new HistoryAdapter(HistoryActivity.this, R.layout.item_history_activity, listHistory);
                lvCalendar = findViewById(R.id.lv_calendar);
                lvCalendar.setAdapter(historyAdapter);
            }
        });
    }

    private void setupUI() {
        materialCalendarView = findViewById(R.id.calendarView);
        ivMenu = findViewById(R.id.iv_menu_drawer);
    }

    private void showPopUp() {
        final PopupMenu popupMenu = new PopupMenu(HistoryActivity.this, ivMenu);
        popupMenu.getMenuInflater().inflate(R.menu.menu_trans, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.menu_overview: {
                        startActivity(new Intent(HistoryActivity.this, MainActivity.class));
                        break;
                    }

                    case R.id.menu_history: {
                        popupMenu.dismiss();
                        break;
                    }

                    default:
                        break;
                }
                return false;
            }
        });
        popupMenu.show();
    }
}
