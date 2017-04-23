package com.sensedog.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.support.annotation.Nullable;
import com.sensedog.function.BiConsumer;
import com.sensedog.function.Consumer;
import com.sensedog.sensor.MovementDetector;
import com.sensedog.sensor.RotationDetector;
import com.sensedog.sensor.VibrationDetector;
import com.sensedog.sensor.unit.Axis;
import com.sensedog.sensor.unit.DetectType;
import com.sensedog.sensor.unit.Direction;

public class AlarmService extends Service {

    private SensorManager manager;
    private Sensor accelerometer;
    private Sensor gravitymeter;
    private Sensor magnetometer;
    private RotationDetector rotationDetector;
    private VibrationDetector vibrationDetector;
    private MovementDetector movementDetector;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        rotationDetector = new RotationDetector();
        rotationDetector.setThreshold(1.0f);
        rotationDetector.setDetectionCallback(new BiConsumer<Axis, Float>() {
            @Override
            public void consume(Axis axis, Float value) {
                detect(DetectType.ROTATION, value);
            }
        });

        vibrationDetector = new VibrationDetector();
        vibrationDetector.setThreshold(10);
        vibrationDetector.setCallback(new Consumer<Float>() {
            @Override
            public void consume(Float value) {
                detect(DetectType.VIBRATION, value);
            }
        });

        movementDetector = new MovementDetector();
        movementDetector.setCallback(new Consumer<Direction>() {
            @Override
            public void consume(Direction direction) {
                detect(DetectType.COMPASS, direction);
            }
        });

        manager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        gravitymeter = manager.getDefaultSensor(Sensor.TYPE_GRAVITY);
        magnetometer = manager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

        manager.registerListener(rotationDetector, accelerometer, SensorManager.SENSOR_DELAY_UI);
        manager.registerListener(vibrationDetector, accelerometer, SensorManager.SENSOR_DELAY_UI);
        manager.registerListener(movementDetector, gravitymeter, SensorManager.SENSOR_DELAY_UI);
        manager.registerListener(movementDetector, magnetometer, SensorManager.SENSOR_DELAY_UI);

        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (manager != null) {
            manager.unregisterListener(rotationDetector, accelerometer);
            manager.unregisterListener(vibrationDetector, accelerometer);
            manager.unregisterListener(movementDetector, gravitymeter);
            manager.unregisterListener(movementDetector, magnetometer);
        }
    }

    private void detect(DetectType detectType, Object value) {
        System.out.println(detectType + ": " + value.toString());
        //Some logics if it's rejected.

        //Check the reason. If it's no master or that the status is stopped, sleep for a minute.
    }
}
