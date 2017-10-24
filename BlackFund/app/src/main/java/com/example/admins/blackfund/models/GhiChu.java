package com.example.admins.blackfund.models;

import java.io.Serializable;

/**
 * Created by Admins on 10/23/2017.
 */

public class GhiChu implements Serializable{
    private String ghiChu;
    private int id;
    private float money;
    private String date;
    private String chonNhom;

    public GhiChu() {

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

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
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

    public GhiChu(String ghiChu, int id, float money, String date, String chonNhom) {
        this.ghiChu = ghiChu;
        this.id = id;
        this.money = money;
        this.date = date;
        this.chonNhom = chonNhom;

    }
}
