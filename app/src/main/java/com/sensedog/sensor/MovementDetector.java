package com.sensedog.sensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import com.sensedog.function.Consumer;
import com.sensedog.sensor.unit.Direction;

public class MovementDetector implements SensorEventListener {

    private float[] gravity;
    private float[] geomagnetic;
    private Direction lastDirection;
    private Consumer<Direction> callback;

    public void setCallback(Consumer<Direction> callback) {
        this.callback = callback;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER || event.sensor.getType() == Sensor.TYPE_GRAVITY) {
            gravity = event.values;
        } else if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            geomagnetic = event.values;
        }

        if (gravity != null && geomagnetic != null) {
            float R[] = new float[9];
            float I[] = new float[9];
            boolean success = SensorManager.getRotationMatrix(R, I, gravity, geomagnetic);

            if (success) {
                float orientation[] = new float[3];
                SensorManager.getOrientation(R, orientation);
                double d = orientation[0] * 180 / Math.PI;

                if (-11.25 < d && d < 11.25)
                   changed(Direction.N);
                else if (11.25 < d && d < 33.75)
                   changed(Direction.NNE);
                else if (33.75 < d && d < 56.25)
                   changed(Direction.NE);
                else if (56.25 < d && d < 78.75)
                   changed(Direction.ENE);
                else if (78.75 < d && d < 101.25)
                   changed(Direction.E);
                else if (101.25 < d && d < 123.75)
                   changed(Direction.ESE);
                else if (123.75 < d && d < 146.25)
                   changed(Direction.SE);
                else if (146.25 < d && d < 168.75)
                   changed(Direction.SSE);
                else if (-33.75 < d && d < -11.25)
                   changed(Direction.NNW);
                else if (-56.25 < d && d < -33.75)
                   changed(Direction.NW);
                else if (-78.75 < d && d < -56.25)
                   changed(Direction.WNW);
                else if (-101.25 < d && d < -78.75)
                   changed(Direction.W);
                else if (-123.75 < d && d < -101.25)
                   changed(Direction.WSW);
                else if (-146.25 < d && d < -123.75)
                   changed(Direction.SW);
                else if (-168.75 < d && d < -146.25)
                   changed(Direction.SSW);
                else
                   changed(Direction.S);
            }
        }
    }
    
    private void changed(Direction direction) {
        if (this.lastDirection != direction) {
            this.lastDirection = direction;
            callback.consume(direction);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
