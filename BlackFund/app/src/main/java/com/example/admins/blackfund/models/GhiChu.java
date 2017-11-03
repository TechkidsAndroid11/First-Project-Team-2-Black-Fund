package com.example.admins.blackfund.models;

import android.util.Log;

import java.io.Serializable;

/**
 * Created by Admins on 10/23/2017.
 */

public class GhiChu implements Serializable{
    private static final String TAG = GhiChu.class.toString();
    private String ghiChu;
    private int id;
    private int money;
    private String date;
    private String chonNhom;
    private boolean isIncome;
    private String dayOfWeek;

    public GhiChu( int id, int money ,boolean isIncome, String chonNhom, String ghiChu) {
        this.id = id;
        this.money = money;
        this.isIncome = isIncome;
        this.chonNhom = chonNhom;
        this.ghiChu = ghiChu;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isIncome() {
        return isIncome;
    }

    public boolean setIsIncome(int setIsIncome) {
        if (setIsIncome == 1){
            return isIncome = true;
        } else {
            return isIncome = false;
        }
    }


    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getDate() {
        return date;
    }

    public String getChonNhom() {
        return chonNhom;
    }

    public void setChonNhom(String chonNhom) {
        this.chonNhom = chonNhom;
    }

    public GhiChu(String ghiChu, int money, String date, String chonNhom) {
        this.ghiChu = ghiChu;
        this.money = money;
        this.date = date;
        this.chonNhom = chonNhom;
    }

    public GhiChu() {
    }

    public int getYear(){
        return Integer.parseInt(getDate().substring(0, 4));
    }

    public int getMonth(){
        return Integer.parseInt(getDate().substring(5, 7));

    public int getDay(){
        return Integer.parseInt(getDate().substring(8,10));
    }
}
