package com.luohong.phoneobserver;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;
import android.widget.TabHost;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView monday;
    private TextView tuesday;
    private TextView wednesday;
    private TextView thursday;
    private TextView friday;
    private TextView saturday;
    private TextView sunday;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         monday = (TextView) findViewById(R.id.tv_monday);
        tuesday = (TextView) findViewById(R.id.tv_tuesday);
        wednesday = (TextView) findViewById(R.id.tv_wednesday);
        thursday = (TextView) findViewById(R.id.tv_thursday);
        friday = (TextView) findViewById(R.id.tv_friday);
        saturday = (TextView) findViewById(R.id.tv_saturday);
        sunday = (TextView) findViewById(R.id.tv_sunday);


    }

    public void daySelect(View view) {

        monday.setSelected(false);
        tuesday.setSelected(false);
        wednesday.setSelected(false);
        thursday.setSelected(false);
        friday.setSelected(false);
        saturday.setSelected(false);
        sunday.setSelected(false);

        switch (view.getId()) {

            case R.id.tv_monday:
                monday.setSelected(true);

                break;
            case R.id.tv_tuesday:
                tuesday.setSelected(true);
                tuesday.setBackgroundColor(0x4DB85A);

                break;
            case R.id.tv_wednesday:
                wednesday.setSelected(true);
                wednesday.setBackgroundColor(0x4DB85A);

                break;
            case R.id.tv_thursday:
                thursday.setSelected(true);
                thursday.setBackgroundColor(0x4DB85A);

                break;
            case R.id.tv_friday:
                friday.setSelected(true);
                friday.setBackgroundColor(0x4DB85A);

                break;
            case R.id.tv_saturday:
                saturday.setSelected(true);
                saturday.setBackgroundColor(0x4DB85A);

                break;

            case R.id.tv_sunday:
                sunday.setSelected(true);
                sunday.setBackgroundColor(0x4DB85A);

                break;

            default:
                break;

        }
    }
}
