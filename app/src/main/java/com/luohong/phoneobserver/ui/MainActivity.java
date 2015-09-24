package com.luohong.phoneobserver.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.luohong.phoneobserver.R;
import com.luohong.phoneobserver.bean.AppInfo;
import com.luohong.phoneobserver.bean.Use;
import com.luohong.phoneobserver.db.UseDb;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends Activity implements View.OnClickListener {
    private TextView tvMonday;
    private TextView tvTuesday;
    private TextView tvWednesday;
    private TextView tvThursday;
    private TextView tvFriday;
    private TextView tvSaturday;
    private TextView tvSunday;
    private ImageView ivMonday;
    private ImageView ivTuesday;
    private ImageView ivWednesday;
    private ImageView ivThursday;
    private ImageView ivFriday;
    private ImageView ivSaturday;
    private ImageView ivSunday;
    private TextView startCount;
    private TextView startTime;
    private TextView yesterdayCount;
    private TextView yesterdayTime;
    private ImageView ivLocation;
    private ImageView ivSatas;
    private UseDb mUseDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();

        setClick();



    }

    private void setClick() {

        ivLocation.setOnClickListener(this);
        ivSatas.setOnClickListener(this);

    }

    private void initView() {

        tvMonday = (TextView) findViewById(R.id.tv_monday);
        tvTuesday = (TextView) findViewById(R.id.tv_tuesday);
        tvWednesday = (TextView) findViewById(R.id.tv_wednesday);
        tvThursday = (TextView) findViewById(R.id.tv_thursday);
        tvFriday = (TextView) findViewById(R.id.tv_friday);
        tvSaturday = (TextView) findViewById(R.id.tv_saturday);
        tvSunday = (TextView) findViewById(R.id.tv_sunday);

        ivMonday = (ImageView) findViewById(R.id.iv_monday);
        ivTuesday = (ImageView) findViewById(R.id.iv_tuesday);
        ivWednesday = (ImageView) findViewById(R.id.iv_wednesday);
        ivThursday = (ImageView) findViewById(R.id.iv_thursday);
        ivFriday = (ImageView) findViewById(R.id.iv_friday);
        ivSaturday = (ImageView) findViewById(R.id.iv_saturday);
        ivSunday = (ImageView) findViewById(R.id.iv_sunday);

        startCount = (TextView) findViewById(R.id.start_count);
        startTime = (TextView) findViewById(R.id.start_time);
        yesterdayCount = (TextView) findViewById(R.id.yesterday_start_count);
        yesterdayTime = (TextView) findViewById(R.id.yesterday_start_time);
        ivLocation = (ImageView) findViewById(R.id.iv_location);
        ivSatas = (ImageView) findViewById(R.id.iv_stats);

    }

    public void initData() {
        Calendar calendar = Calendar.getInstance();
        int week = calendar.get(Calendar.DAY_OF_WEEK);// 国外是从周日开始
        mUseDb = new UseDb(this);
        switch (week) {
            case 1:
                daySelect(tvSunday);
                break;
            case 2:
                daySelect(tvMonday);
                break;
            case 3:
                daySelect(tvTuesday);
                break;
            case 4:
                daySelect(tvWednesday);
                break;
            case 5:
                daySelect(tvThursday);
                break;
            case 6:
                daySelect(tvFriday);
                break;
            case 7:
                daySelect(tvSaturday);
                break;
        }


    }

    //周几的监听
    public void daySelect(View view) {

        tvMonday.setSelected(false);
        ivMonday.setSelected(false);
        tvTuesday.setSelected(false);
        ivTuesday.setSelected(false);
        tvWednesday.setSelected(false);
        ivWednesday.setSelected(false);
        tvThursday.setSelected(false);
        ivThursday.setSelected(false);
        tvFriday.setSelected(false);
        ivFriday.setSelected(false);
        tvSaturday.setSelected(false);
        ivSaturday.setSelected(false);
        tvSunday.setSelected(false);
        ivSunday.setSelected(false);

        switch (view.getId()) {
            case R.id.tv_monday:
                tvMonday.setSelected(true);
                ivMonday.setSelected(true);

                getOpenCountByDate(2);
                break;
            case R.id.tv_tuesday:
                tvTuesday.setSelected(true);
                ivTuesday.setSelected(true);
                getOpenCountByDate(3);
                break;
            case R.id.tv_wednesday:
                tvWednesday.setSelected(true);
                ivWednesday.setSelected(true);
                getOpenCountByDate(4);
                break;
            case R.id.tv_thursday:
                tvThursday.setSelected(true);
                ivThursday.setSelected(true);
                getOpenCountByDate(5);
                break;
            case R.id.tv_friday:
                tvFriday.setSelected(true);
                ivFriday.setSelected(true);
                getOpenCountByDate(6);
                break;
            case R.id.tv_saturday:
                tvSaturday.setSelected(true);
                ivSaturday.setSelected(true);
                getOpenCountByDate(7);
                break;

            case R.id.tv_sunday:
                tvSunday.setSelected(true);
                ivSunday.setSelected(true);
                getOpenCountByDate(8);
                break;

            default:
                break;

        }
    }

    private void getOpenCountByDate(int week) {
        int dateOfWeek = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
        String selectDate = searchStartCount(week, dateOfWeek);

        startCount.setText(mUseDb.findOpenCountByDate(selectDate) + "");

        List<Use> list = mUseDb.findAllOnAndOff(selectDate);

        long offset = 0;
        for (int i = 0; i < list.size(); ) {
            Use use = list.get(i);
            if ("on".equals(use.action)) {
                i = i + 1;
                if (i < list.size()) {
                    Use next = list.get(i);
                    if ("off".equals(next.action)) {
                        offset += next.time - use.time;
                    }
                    i = i + 1;
                } else if (dateOfWeek == week || (week == 8 && dateOfWeek == 1)) {// 当前正在使用
                    offset += System.currentTimeMillis() - use.time;
                }
            } else {
                i = i + 1;
            }
        }

        int hour = (int) (offset / (60 * 60 * 1000));
        int minite = (int) ((offset % (60 * 60 * 1000)) / (60 * 1000));
        int secend = (int) ((offset % (60 * 1000)) / 1000);

        StringBuffer time = new StringBuffer();
        if (Integer.toString(hour).length() == 1) {
            time.append("0");
        }
        time.append(hour).append(":");
        if (Integer.toString(minite).length() == 1) {
            time.append("0");
        }
        time.append(minite).append(":");
        if (Integer.toString(secend).length() == 1) {
            time.append("0");
        }
        time.append(secend);
        startTime.setText(time);
    }

    private String searchStartCount(int date, int dateOfWeek) {
        int days = date - dateOfWeek;

        Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.DAY_OF_MONTH, days);
        Date choosedDate = calendar.getTime();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式
        return sdf.format(choosedDate);    //格式化

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_location:
                startActivity(new Intent(this, MapActivity.class));
                break;

            case R.id.iv_stats:
                Intent intent = new Intent(this, StatsTimeActivity.class);
                startActivity(intent);
                break;

        }

    }

    @Override
    protected void onResume() {
        super.onResume();

        int week = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
        if (week == 1) week = 8;
        getOpenCountByDate(week);
    }

    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
