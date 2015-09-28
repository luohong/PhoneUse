package com.luohong.phoneobserver.bean;

import com.luohong.phoneobserver.util.DateUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by luohong on 15/9/22.
 */
public class Use {

    public int id;
    public String date;
    public long startTime;
    public long endTime;
    public String desc;

    public Use() {
        this.startTime = System.currentTimeMillis();
        this.date = DateUtil.getToday();
        this.desc = "";
    }

}
