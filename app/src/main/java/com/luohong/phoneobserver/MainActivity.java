package com.luohong.phoneobserver;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvMonday = (TextView) findViewById(R.id.tv_monday);
        ivMonday = (ImageView) findViewById(R.id.iv_monday);
        tvTuesday = (TextView) findViewById(R.id.tv_tuesday);
        ivTuesday = (ImageView) findViewById(R.id.iv_tuesday);
        tvWednesday = (TextView) findViewById(R.id.tv_wednesday);
        ivWednesday = (ImageView) findViewById(R.id.iv_wednesday);
        tvThursday = (TextView) findViewById(R.id.tv_thursday);
        ivThursday = (ImageView) findViewById(R.id.iv_thursday);
        tvFriday = (TextView) findViewById(R.id.tv_friday);
        ivFriday = (ImageView) findViewById(R.id.iv_friday);
        tvSaturday = (TextView) findViewById(R.id.tv_saturday);
        ivSaturday = (ImageView) findViewById(R.id.iv_saturday);
        tvSunday = (TextView) findViewById(R.id.tv_sunday);
        ivSunday = (ImageView) findViewById(R.id.iv_sunday);


    }

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
}
