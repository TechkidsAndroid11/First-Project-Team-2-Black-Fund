package com.example.admins.blackfund.utils;

import android.widget.ImageView;

import com.example.admins.blackfund.R;
import com.example.admins.blackfund.models.GhiChu;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Son Hoang on 11/2/2017.
 */

public class ReadDataUtils {
    public static ReadDataUtils readDataUtils;

    public static ReadDataUtils getInstance() {
        if (readDataUtils == null) {
            readDataUtils = new ReadDataUtils();
        }
        return readDataUtils;
    }

    public ReadDataUtils() {

    }

    public void setImageResource(ImageView imageView, String categoryName) {
        switch (categoryName) {
            case "FOODS": {
                imageView.setImageResource(R.drawable.food);
                break;
            }
            case "FRIENDS": {
                imageView.setImageResource(R.drawable.banbe);
                break;
            }

            case "SHOPPING": {
                imageView.setImageResource(R.drawable.shopping);
                break;
            }

            case "ENTERTAINMENT": {
                imageView.setImageResource(R.drawable.giaitri);
                break;
            }

            case "TRANSPORTATION": {
                imageView.setImageResource(R.drawable.phuongtien);
                break;
            }

            case "LOVE": {
                imageView.setImageResource(R.drawable.love);
                break;
            }

            case "WORKS": {
                imageView.setImageResource(R.drawable.works);
                break;
            }

            case "OTHERS": {
                imageView.setImageResource(R.drawable.others);
                break;
            }
        }
    }

    public String formatDatabaseDate(String date) {
        String formattedDate = "";
        SimpleDateFormat dateParser = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date selectedDate = null;
        try {
            selectedDate = dateParser.parse(date);
            SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
            formattedDate = dateFormatter.format(selectedDate);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formattedDate;
    }

    public String formatActivityDate(String date) {
        String formattedDate = "";
        SimpleDateFormat dateParser = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date selectedDate = null;
        try {
            selectedDate = dateParser.parse(date);
            SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
            formattedDate = dateFormatter.format(selectedDate);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formattedDate;
    }
}
