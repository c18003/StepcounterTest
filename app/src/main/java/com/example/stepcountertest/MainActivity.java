package com.example.stepcountertest;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;
import android.widget.TextView;
import android.os.Bundle;

public class MainActivity extends Activity implements SensorEventListener {

    private SensorManager manager;
    private Sensor stepCntSensor;
    private int stepcount2 = 0;

    private TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager = (SensorManager)getSystemService(SENSOR_SERVICE);

        stepCntSensor = manager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);

        textView2 = (TextView)findViewById(R.id.textView2);

        textView2.setText(" ");
    }





    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy){

    }

    @Override
    public void onSensorChanged(SensorEvent event){

        Sensor sensor = event.sensor;
        float[] values = event.values;
        long timestamp = event.timestamp;


        if(sensor.getType() == sensor.TYPE_STEP_COUNTER){
            Log.d("type_step_counter",String.valueOf(values[0]));
            stepcount2++;
            textView2.setText(" " + stepcount2 + " /歩");
        }
    }

    @Override
    protected void onResume(){
        super.onResume();

        manager.registerListener(this,stepCntSensor,SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    protected void onPause(){
        super.onPause();

        manager.unregisterListener(this,stepCntSensor);
    }
}
