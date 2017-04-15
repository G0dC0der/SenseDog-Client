package com.sensedog.sensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import com.sensedog.function.Consumer;

public class VibrationDetector implements SensorEventListener {

    private float threshold = 800;
    private float frequency = 100;
    private float prevX;
    private float prevY;
    private float prevZ;
    private long prevTime;
    private Consumer<Float> callback;

    public void setThreshold(float threshold) {
        this.threshold = threshold;
    }

    public void setFrequency(float frequency) {
        this.frequency = frequency;
    }

    public void setCallback(Consumer<Float> callback) {
        this.callback = callback;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        long now = System.currentTimeMillis();

        if (now - prevTime > frequency) {
            long diffTime = (now - prevTime);
            prevTime = now;

            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            float shakeSpeed = Math.abs(x+y+z - prevX - prevY - prevZ) / diffTime * 10000;

            callback.consume(shakeSpeed > threshold ? shakeSpeed : null);

            prevX = x;
            prevY = y;
            prevZ = z;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
