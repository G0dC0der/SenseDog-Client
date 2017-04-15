package com.sensedog.bind;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.webkit.JavascriptInterface;
import com.sensedog.service.AlarmService;

public class AndroidServiceBindings {

    private final AppCompatActivity activity;

    public AndroidServiceBindings(AppCompatActivity activity) {
        this.activity = activity;
    }

    @JavascriptInterface
    public void startAlarmService() {
        activity.startService(new Intent(activity, AlarmService.class));
    }

    @JavascriptInterface
    public void stopAlarmService() {
        activity.stopService(new Intent(activity, AlarmService.class));
    }

    @JavascriptInterface
    public boolean isAlarmServiceRunning() {
        ActivityManager manager = (ActivityManager) activity.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (AlarmService.class.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    //TODO: Same but for master
}
