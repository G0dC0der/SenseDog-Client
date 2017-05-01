package com.sensedog.sensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

public abstract class SensorEventEmitter implements SensorEventListener {

    private boolean paused;
    private long resumeDate;

    abstract void sensorEvent(SensorEvent sensorEvent);

    public void pause() {
        paused = true;
    }

    public void resume() {
        paused = false;
    }

    public void snooze(long millis) {
        resumeDate = System.currentTimeMillis() + millis;
    }

    @Override
    public final void onSensorChanged(SensorEvent event) {
        if (paused) {
            return;
        }
        if (resumeDate != -1 && System.currentTimeMillis() > resumeDate) {
            resumeDate = -1;
        }
        if (resumeDate == -1) {
            sensorEvent(event);
        }
    }

    @Override
    public final void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
