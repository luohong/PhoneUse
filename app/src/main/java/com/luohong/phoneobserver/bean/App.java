package com.luohong.phoneobserver.bean;

import android.graphics.drawable.Drawable;

import com.luohong.phoneobserver.util.DateUtil;

/**
 * Created by yaya on 2015/9/24.
 */
public class App {

    public String name ;
    public Drawable icon;

    public String packageName;

    public int id;
    public String date;
    public long startTime;
    public long endTime;
    public String desc;

    public App() {

    }

    public App(String packageName) {
        this.packageName = packageName;

        this.startTime = System.currentTimeMillis();
        this.date = DateUtil.getToday();
        this.desc = "";
    }

}
