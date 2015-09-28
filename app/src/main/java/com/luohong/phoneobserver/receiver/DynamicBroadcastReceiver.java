package com.luohong.phoneobserver.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

/**
 * Created by luohong on 15/9/24.
 */
public class DynamicBroadcastReceiver extends BroadcastReceiver {

    public static IntentFilter buildIntentFilter() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_ON);//屏幕亮
        filter.addAction(Intent.ACTION_SCREEN_OFF);//屏幕灭
        filter.addAction(Intent.ACTION_TIME_TICK);//时间变化  每分钟一次
        filter.addAction(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);// HOME键
        return filter;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            switch (intent.getAction()) {
                case Intent.ACTION_SCREEN_ON:
                    break;
                case Intent.ACTION_SCREEN_OFF:
                    break;
                case Intent.ACTION_TIME_TICK:
                    restartObserverServiceIfDie();
                    break;
                case Intent.ACTION_CLOSE_SYSTEM_DIALOGS:
                    break;
            }

        } else {
            restartObserverServiceIfDie();
        }
    }

    private void restartObserverServiceIfDie() {

    }

}
