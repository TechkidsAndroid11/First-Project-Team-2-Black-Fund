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
    private String money;
    private String date;
    private String chonNhom;
    private boolean isIncome;

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

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getChonNhom() {
        return chonNhom;
    }

    public void setChonNhom(String chonNhom) {
        this.chonNhom = chonNhom;
    }

    public GhiChu(String ghiChu, String money, String date, String chonNhom) {
        this.ghiChu = ghiChu;
        this.money = money;
        this.date = date;
        this.chonNhom = chonNhom;
    }

    public GhiChu() {
    }
}
