package com.luohong.phoneobserver;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

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

                break;
            case R.id.tv_tuesday:
                tvTuesday.setSelected(true);
                ivTuesday.setSelected(true);

                break;
            case R.id.tv_wednesday:
                tvWednesday.setSelected(true);
                ivWednesday.setSelected(true);

                break;
            case R.id.tv_thursday:
                tvThursday.setSelected(true);
                ivThursday.setSelected(true);

                break;
            case R.id.tv_friday:
                tvFriday.setSelected(true);
                ivFriday.setSelected(true);

                break;
            case R.id.tv_saturday:
                tvSaturday.setSelected(true);
                ivSaturday.setSelected(true);

                break;

            case R.id.tv_sunday:
                tvSunday.setSelected(true);
                ivSunday.setSelected(true);

                break;

            default:
                break;

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_location:

                break;

            case R.id.iv_stats:
                Intent intent = new Intent(this, StatsTimeActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }

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
