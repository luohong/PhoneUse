package com.luohong.phoneobserver.ui;

import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.luohong.phoneobserver.R;
import com.luohong.phoneobserver.adapter.StatsTimeListViewAdapter;
import com.luohong.phoneobserver.bean.AppInfo;

import java.util.ArrayList;
import java.util.List;

public class StatsTimeActivity extends Activity implements View.OnClickListener {
    private ListView mListView;
    private StatsTimeListViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_satasime);

        ImageView back = (ImageView) findViewById(R.id.iv_back);

        PackageManager packageManager = this.getPackageManager();
        List<AppInfo> infos = new ArrayList<>();

        List<PackageInfo> packageInfos = packageManager.getInstalledPackages(PackageManager.GET_UNINSTALLED_PACKAGES);
        AppInfo appInfo;
        for (int i = 0; i < packageInfos.size(); i++) {
            appInfo = new AppInfo();
            ApplicationInfo applicationInfo = packageInfos.get(i).applicationInfo;
            Drawable icon = applicationInfo.loadIcon(packageManager);
            String name = applicationInfo.loadLabel(packageManager).toString();
            appInfo.setAppIcon(icon);
            appInfo.setAppName(name);

            infos.add(appInfo);

            System.out.println("77777777" + infos.size());
        }


        mListView = (ListView) findViewById(R.id.lv_stats);
         mAdapter = new StatsTimeListViewAdapter(this, infos);
        mListView.setAdapter(mAdapter);

        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.iv_back:

                finish();

                break;

            default:
                break;
        }
    }

}
