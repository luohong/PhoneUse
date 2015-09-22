package com.luohong.phoneobserver.bean;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by luohong on 15/9/22.
 */
public class Use {

    public int id;
    public String date;
    public long time;
    public String action;
    public String desc;

    public Use() {

    }

    public Use(String action) {
        this.action = action;

        this.time = System.currentTimeMillis();

        this.date = getToday();

        this.desc = "";
    }

    public static String getToday() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }
}
