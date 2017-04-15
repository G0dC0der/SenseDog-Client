package com.sensedog.sensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import com.sensedog.sensor.unit.Axis;
import com.sensedog.function.BiConsumer;

public class RotationDetector implements SensorEventListener {

    private static final int CALIBRATING_TIMES = 10;

    private float calibratedX;
    private float calibratedY;
    private float calibratedZ;
    private float threshold;
    private int calibratingCounter;
    private boolean calibrating;
    private float[] bufferX;
    private float[] bufferY;
    private float[] bufferZ;
    private BiConsumer<Axis, Float> callback;

    public RotationDetector() {
        bufferX = new float[CALIBRATING_TIMES];
        bufferY = new float[CALIBRATING_TIMES];
        bufferZ = new float[CALIBRATING_TIMES];
    }

    public void setDetectionCallback(BiConsumer<Axis, Float> detectionCallback) {
        this.callback = detectionCallback;
    }

    public void calibrate() {
        calibrating = true;
    }

    public void setThreshold(float threshold) {
        this.threshold = threshold;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (calibrating) {
            final int counter = ++calibratingCounter % CALIBRATING_TIMES;

            if (counter == 0) {
                calibrating = false;
                calibratedX = average(bufferX);
                calibratedY = average(bufferY);
                calibratedZ = average(bufferZ);
            } else {
                bufferX[counter] = event.values[0];
                bufferY[counter] = event.values[1];
                bufferZ[counter] = event.values[2];
            }
        } else {
            if (Math.abs(calibratedX - event.values[0]) > threshold) {
                callback.consume(Axis.X, event.values[0]);
            } else if (Math.abs(calibratedY - event.values[1]) > threshold) {
                callback.consume(Axis.Y, event.values[1]);
            } else if (Math.abs(calibratedZ - event.values[2]) > threshold) {
                callback.consume(Axis.Z, event.values[2]);
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {}

    private float average(float[] arr) {
        float sum = 0;
        for (float f : arr) {
            sum += f;
        }

        return sum / arr.length;
    }
}
