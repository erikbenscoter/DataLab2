package com.project2015.datalab2.wellnessnet;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

/**
 * Created by erikbenscoter on 3/26/15.
 */
public class Sensors implements SensorEventListener {

    private SensorManager mySensorManager;          //will be used to check what hardware is available
    private Sensor myAccelerometer;                 //will be used to check the accelerometer
    double gravity[] = new double[3];               //will hold accelerometer values for x,y,z

    public Sensors(Activity act){
        //get a list of the default hardware installed
        mySensorManager = (SensorManager) act.getSystemService(Context.SENSOR_SERVICE);
        //get default accelerometer
        myAccelerometer = mySensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mySensorManager.registerListener(this,myAccelerometer,SensorManager.SENSOR_DELAY_NORMAL);

        //System.out.println("ACCELEROMETER SAYS I'M BEING INITIALIZED \n");
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        final double alpha = 0.8;



        //used to isolate the force of gravity
        gravity[0] = alpha * gravity[0] + (1 - alpha) * event.values[0];
        gravity[1] = alpha * gravity[1] + (1 - alpha) * event.values[1];
        gravity[2] = alpha * gravity[2] + (1 - alpha) * event.values[2];

        //System.out.println("ACCELEROMETER SAYS I'M BEING CHANGED \n");

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    ////////////////////////////////Setters and Getters/////////////////////////////////////////////
    public double getX(){return this.gravity[0];}
    public double getY(){return this.gravity[1];}
    public double getZ(){return this.gravity[2];}


}
