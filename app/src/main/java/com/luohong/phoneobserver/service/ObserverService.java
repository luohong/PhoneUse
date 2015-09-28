package com.luohong.phoneobserver.service;

import android.app.ActivityManager;
import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.util.Log;

import java.util.Arrays;
import java.util.List;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class ObserverService extends IntentService {
    // TODO: Rename actions, choose endTime names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_MONITOR = "com.luohong.phoneobserver.service.endTime.Monitor";
    private static final String ACTION_BAZ = "com.luohong.phoneobserver.service.endTime.BAZ";

    // TODO: Rename parameters
    private static final String EXTRA_PARAM1 = "com.luohong.phoneobserver.service.extra.PARAM1";
    private static final String EXTRA_PARAM2 = "com.luohong.phoneobserver.service.extra.PARAM2";
    private static final String TAG = ObserverService.class.getSimpleName();

    /**
     * Starts this service to perform endTime Foo with the given parameters. If
     * the service is already performing a task this endTime will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionMonitor(Context context) {
        Intent intent = new Intent(context, ObserverService.class);
        intent.setAction(ACTION_MONITOR);
        context.startService(intent);
    }

    /**
     * Starts this service to perform endTime Baz with the given parameters. If
     * the service is already performing a task this endTime will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionBaz(Context context, String param1, String param2) {
        Intent intent = new Intent(context, ObserverService.class);
        intent.setAction(ACTION_BAZ);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    public ObserverService() {
        super("ObserverService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_MONITOR.equals(action)) {
//                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
//                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
//                handleActionMonitor(param1, param2);
                handleActionMonitor();
            } else if (ACTION_BAZ.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionBaz(param1, param2);
            }
        }
    }

    private void handleActionMonitor() {
        ActivityManager mActivityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        // 通过调用ActivityManager的getRunningAppProcesses()方法获得系统里所有正在运行的进程
        List<ActivityManager.RunningAppProcessInfo> appProcessList = mActivityManager.getRunningAppProcesses();

        ActivityManager.RunningAppProcessInfo appProcess = appProcessList.get(0);
        String[] pkgList = appProcess.pkgList;
        if (pkgList != null && pkgList.length > 0) {
            Log.d(TAG, "running app process pkgList: " + Arrays.toString(pkgList));
        }

    }

    /**
     * Handle endTime Baz in the provided background thread with the provided
     * parameters.
     */
    private void handleActionBaz(String param1, String param2) {
        // TODO: Handle endTime Baz
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
