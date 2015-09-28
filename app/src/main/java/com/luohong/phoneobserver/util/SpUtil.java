package com.luohong.phoneobserver.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.luohong.phoneobserver.AppController;

import java.util.Map;

public class SpUtil {
	private static final String NAME = "sp_play_count";

	private static final int SYNC = 1000;
	private boolean hasStartMonitor = false;

	public static SpUtil instance = null;

	private Context context;

	private SpUtil(Context context) {
		this.context = context;
	}

	public static SpUtil getInstance() {
		Context applicationContext = AppController.getInstance();
		if (null == instance || instance.context != applicationContext) {
			instance = new SpUtil(applicationContext);
		}
		return instance;
	}

	private SharedPreferences sp;

	public SharedPreferences getSp() {
		if (sp == null)
			sp = context.getSharedPreferences(getSpFileName(),
					Context.MODE_PRIVATE);
		return sp;
	}

	public Editor getEdit() {
		return getSp().edit();
	}

	private String getSpFileName() {
		return NAME;
	}

	public void clear(){
		getEdit().clear().commit();
	}

	private Map<String, Integer> getAll() {
		return (Map<String, Integer>) getSp().getAll();
	}

	public long getAppStartTime() {
		return getSp().getLong("AppStartTime", System.currentTimeMillis());
	}

	public void setAppStartTime() {
		getEdit().putLong("AppStartTime", System.currentTimeMillis()).commit();
	}

	public long getAppUpdateTime() {
		return getSp().getLong("AppUpdateTime", getAppStartTime());
	}

	public void setAppUpdateTime() {
		getEdit().putLong("AppUpdateTime", System.currentTimeMillis()).commit();
	}

	public boolean isSafeExit() {
		return getSp().getBoolean("isSafeExit", false);
	}

	public void setSafeExit() {
		getEdit().putBoolean("isSafeExit", true).commit();
	}
}
