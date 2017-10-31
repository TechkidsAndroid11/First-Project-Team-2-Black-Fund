package com.example.admins.blackfund.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.admins.blackfund.models.GhiChu;

import java.util.ArrayList;

public class BlackFundDatabase extends SQLiteOpenHelper {
    public static final String TABLE_NOTE = "TB_GHiCHU";
    public static final String DATABASE_NAME = "ghiChu.db";
    public static final int DATA_VERSION = 1;
    public static final String KEY_ID_GHICHU = "id";
    public static final String KEY_TIEN = "TIEN";
    public static final String KEY_GHICHU = "GHICHU";
    public static final String KEY_DATE = "DATE";
    private static final String KEY_CHONNHOM = "LYDO";

    public static final String CREAT_TABLE_GHICHU = "CREATE TABLE " +
            TABLE_NOTE + "(" +
            KEY_ID_GHICHU + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL" + ", " +
            KEY_TIEN + " TEXT NOT NULL" + "," +
            KEY_CHONNHOM + " TEXT NOT NULL" + "," +
            KEY_GHICHU + " TEXT NOT NULL " + "," +
            KEY_DATE + " TEXT NOT NULL " + ")";

    private static final String TAG = BlackFundDatabase.class.toString();
    private SQLiteDatabase db;
    public static BlackFundDatabase blackFundDatabase;

    public static BlackFundDatabase getInstance(Context context) {
        if (blackFundDatabase == null) {
            blackFundDatabase = new BlackFundDatabase(context);
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
            sqLiteDatabase.execSQL(CREAT_TABLE_GHICHU);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public ArrayList<GhiChu> getListGhiChu() {
        ArrayList<GhiChu> list = new ArrayList<>();
        db = getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from TB_GHiCHU", null);
        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                GhiChu ghiChu = new GhiChu();
                ghiChu.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID_GHICHU)));
                ghiChu.setMoney(cursor.getString(cursor.getColumnIndex(KEY_TIEN)));
                ghiChu.setChonNhom(cursor.getString(cursor.getColumnIndex(KEY_CHONNHOM)));
                ghiChu.setGhiChu(cursor.getString(cursor.getColumnIndex(KEY_GHICHU)));
                ghiChu.setDate(cursor.getString(cursor.getColumnIndex(KEY_DATE)));

                Log.d(TAG, "getListNote: " + ghiChu.getId());

                list.add(ghiChu);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return list;
    }

    public long addGhiChu(GhiChu note) {
        ContentValues values = new ContentValues();
        values.put(KEY_DATE, note.getDate());
        values.put(KEY_CHONNHOM, note.getChonNhom());
        values.put(KEY_GHICHU, note.getGhiChu());
        values.put(KEY_TIEN, note.getMoney());
        long index = db.insert(TABLE_NOTE, null, values);
        close();
        return index;

    }
}
