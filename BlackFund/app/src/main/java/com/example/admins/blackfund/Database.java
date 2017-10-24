package com.example.admins.blackfund;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Admins on 10/23/2017.
 */

public class Database extends SQLiteOpenHelper {
    public static final String TABLE_NOTE = "TB_GHiCHU";
    public static final String DATABASE_NAME = "ghiChu.db";

    public static final int DATA_VERSION = 1;
    public static final String KEY_ID_GHICHU = "id";
    public static final String KEY_TIEN = "TIEN";
    public static final String KEY_GHICHU = "GHICHU";
    public static final String KEY_DATE = "DATE";
    private static final String KEY_CHONNHOM = "LYDO";


    public static final String CREAT_TABLE_GHICHU = "CREATE TABLE " + TABLE_NOTE + "(" +
            KEY_ID_GHICHU + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL" +
            ", " + KEY_TIEN + " TEXT NOT NULL" +
            "," + KEY_CHONNHOM + " TEXT NOT NULL" + "," + KEY_GHICHU + " TEXT NOT NULL " + "," + KEY_DATE + " TEXT NOT NULL " + ")";
    private static final String TAG = Database.class.toString();

    private SQLiteDatabase db;
    public static Database database;

    public static Database getInstance(Context context) {
        if (database == null) {
            database = new Database(context);
        }
        return database;
    }

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATA_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try {
            // tao database
            db.execSQL(CREAT_TABLE_GHICHU);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public ArrayList<GhiChu> getListGhiChu() {
        ArrayList<GhiChu> list = new ArrayList<>();
        getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from tb_note", null);
        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                GhiChu ghiChu = new GhiChu();
                ghiChu.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID_GHICHU)));
                ghiChu.setMoney(cursor.getFloat(cursor.getColumnIndex(KEY_TIEN)));
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
}
