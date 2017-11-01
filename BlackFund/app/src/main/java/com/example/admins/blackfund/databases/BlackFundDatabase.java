package com.example.admins.blackfund.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.admins.blackfund.activities.MainActivity;
import com.example.admins.blackfund.models.GhiChu;

import java.util.ArrayList;

public class BlackFundDatabase extends SQLiteOpenHelper {
    private static final String TABLE_NOTE = "TB_GHICHU";
    private static final String DATABASE_NAME = "ghiChu.db";
    private static final int DATA_VERSION = 1;
    private static final String KEY_ID_GHICHU = "id";
    private static final String KEY_TIEN = "TIEN";
    private static final String KEY_ISINCOME = "PHANLOAI";
    private static final String KEY_GHICHU = "GHICHU";
    private static final String KEY_DATE = "NGAYTHANG";
    private static final String KEY_CHONNHOM = "LYDO";
    private static final String KEY_DAYOFWEEK = "DAYOFWEEK";


    private static final String CREATE_TABLE_GHICHU = "CREATE TABLE " +
            TABLE_NOTE + "(" +
            KEY_ID_GHICHU + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL" + ", " +
            KEY_TIEN + " INTEGER NOT NULL" + "," +
            KEY_ISINCOME + " INTEGER NOT NULL" + "," +
            KEY_CHONNHOM + " TEXT NOT NULL" + "," +
            KEY_GHICHU + " TEXT NOT NULL " + "," +
            KEY_DATE + " TEXT NOT NULL " + ")";

    private static final String TAG = BlackFundDatabase.class.toString();
    public static BlackFundDatabase blackFundDatabase;

    public static BlackFundDatabase getInstance(Context context) {
        if (blackFundDatabase == null) {
            blackFundDatabase = new BlackFundDatabase(context);
            Log.d(TAG, "getInstance: " + context.getDatabasePath(DATABASE_NAME));
        }
        return blackFundDatabase;
    }

    public BlackFundDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATA_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try {
            // tao blackFundDatabase
            sqLiteDatabase.execSQL(CREATE_TABLE_GHICHU);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public ArrayList<GhiChu> getListGhiChu() {
        ArrayList<GhiChu> list = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("select *, " +
                "case cast (strftime('%w', ngaythang) as integer)\n" +
                "\twhen 0 then 'Sunday'\n" +
                "\twhen 1 then 'Monday'\n" +
                "\twhen 2 then 'Tuesday'\n" +
                "\twhen 3 then 'Wednesday'\n" +
                "\twhen 4 then 'Thursday'\n" +
                "\twhen 5 then 'Friday'\n" +
                "\telse 'Saturday' end as " + KEY_DAYOFWEEK + " from TB_GHICHU order by id desc limit 3",
                null);
        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                GhiChu ghiChu = new GhiChu();
                ghiChu.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID_GHICHU)));
                ghiChu.setMoney(cursor.getInt(cursor.getColumnIndex(KEY_TIEN)));
                ghiChu.setChonNhom(cursor.getString(cursor.getColumnIndex(KEY_CHONNHOM)));
                ghiChu.setGhiChu(cursor.getString(cursor.getColumnIndex(KEY_GHICHU)));
                ghiChu.setDate(cursor.getString(cursor.getColumnIndex(KEY_DATE)));
                ghiChu.setIsIncome(cursor.getInt(cursor.getColumnIndex(KEY_ISINCOME)));
                ghiChu.setDayOfWeek(cursor.getString(cursor.getColumnIndex(KEY_DAYOFWEEK)));

                list.add(ghiChu);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return list;
    }

    public long addGhiChu(GhiChu note, boolean isIncome) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_DATE, note.getDate());
        values.put(KEY_CHONNHOM, note.getChonNhom());
        values.put(KEY_GHICHU, note.getGhiChu());
        values.put(KEY_TIEN, note.getMoney());
        if (isIncome) {
            values.put(KEY_ISINCOME, 1);
        } else {
            values.put(KEY_ISINCOME, 0);
        }
        long index = db.insert(TABLE_NOTE, null, values);
        close();
        return index;
    }

    public int calculateIncome(int calculatedMonth) {
        int totalIncome = 0;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select sum(" +
                KEY_TIEN + ") from " +
                TABLE_NOTE + " where " +
                KEY_ISINCOME + " = 1 and strftime('%m', ngaythang) = '" + calculatedMonth +"' order by id",
                null);
        if (cursor != null){
            cursor.moveToFirst();
            totalIncome = cursor.getInt(0);
        }
        Log.d(TAG, "calculateIncome: " + DatabaseUtils.dumpCursorToString(cursor));
        return totalIncome;
    }

    public int calculateExpense(int calculatedMonth) {
        int totalExpense = 0;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select sum(" +
                        KEY_TIEN + ") from " +
                        TABLE_NOTE + " where " +
                        KEY_ISINCOME + " = 0 and strftime('%m', ngaythang) = '" + calculatedMonth +"' order by id",
                null);
        if (cursor != null){
            cursor.moveToFirst();
            totalExpense = cursor.getInt(0);
        }
        Log.d(TAG, "calculateIncome: " + DatabaseUtils.dumpCursorToString(cursor));
        return totalExpense;
    }

}
