package com.luohong.phoneobserver.bean;

import android.graphics.drawable.Drawable;

/**
 * Created by yaya on 2015/9/24.
 */
public class AppInfo {
    private String appName ;
    private Drawable appIcon;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public Drawable getAppIcon() {
        return appIcon;
    }

    public void setAppIcon(Drawable appIcon) {
        this.appIcon = appIcon;
    }
}
