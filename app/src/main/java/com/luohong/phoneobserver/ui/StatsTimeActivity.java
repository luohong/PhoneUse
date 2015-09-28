package com.luohong.phoneobserver.ui;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.luohong.phoneobserver.R;
import com.luohong.phoneobserver.adapter.StatsTimeListViewAdapter;
import com.luohong.phoneobserver.bean.App;
import com.luohong.phoneobserver.bean.Use;
import com.luohong.phoneobserver.db.AppDb;
import com.luohong.phoneobserver.util.DateUtil;
import com.luohong.phoneobserver.util.SpUtil;

import java.util.ArrayList;
import java.util.List;

public class StatsTimeActivity extends Activity implements View.OnClickListener {
    private static final String TAG = StatsTimeActivity.class.getSimpleName();
    private ListView mListView;
    private StatsTimeListViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_satasime);

        ImageView back = (ImageView) findViewById(R.id.iv_back);

        List<App> infos = new ArrayList<>();

        PackageManager packageManager = this.getPackageManager();
        List<PackageInfo> packageInfos = packageManager.getInstalledPackages(PackageManager.GET_UNINSTALLED_PACKAGES);

        App appInfo;
        PackageInfo packageInfo = null;
        for (int i = 0; i < packageInfos.size(); i++) {
            packageInfo = packageInfos.get(i);
            if (((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) <= 0)) {// 非系统应用
                ApplicationInfo applicationInfo = packageInfo.applicationInfo;

                appInfo = new App();
                appInfo.name = applicationInfo.loadLabel(packageManager).toString();
                appInfo.icon = applicationInfo.loadIcon(packageManager);

                infos.add(appInfo);
            }
        }
        System.out.println("77777777" + infos.size());

        mListView = (ListView) findViewById(R.id.lv_stats);
        mAdapter = new StatsTimeListViewAdapter(this, infos);
        mListView.setAdapter(mAdapter);

        back.setOnClickListener(this);

        handleActionMonitor();
    }

    private void handleActionMonitor() {
        ActivityManager mActivityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        // 通过调用ActivityManager的getRunningAppProcesses()方法获得系统里所有正在运行的进程
        List<ActivityManager.RunningAppProcessInfo> appProcessList = mActivityManager.getRunningAppProcesses();

        ActivityManager.RunningAppProcessInfo appProcess = appProcessList.get(0);
        String[] pkgList = appProcess.pkgList;
        if (pkgList != null && pkgList.length > 0) {
            String packageName = pkgList[0];
            if (!TextUtils.isEmpty(packageName)) {
                AppDb mAppDb = new AppDb(this);

                String today = DateUtil.getToday();
                App app = mAppDb.findLastOne(packageName, today);
                if (app != null) {
                    app.endTime = System.currentTimeMillis();
                    mAppDb.update(app);
                } else {
                    app = new App();

                    String yesterday = DateUtil.getYesterday();
                    App yesterdayLastUse = mAppDb.findLastOne(packageName, yesterday);
                    if (yesterdayLastUse != null) {
                        yesterdayLastUse.endTime = DateUtil.getYesterdayEndTime();
                        mAppDb.update(yesterdayLastUse);

                        app.startTime = yesterdayLastUse.endTime + 1;
                    } else {
                        app.startTime = SpUtil.getInstance().getAppStartTime();
                    }
                    app.endTime = System.currentTimeMillis();
                    mAppDb.insert(app);
                }
            }
        }


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
