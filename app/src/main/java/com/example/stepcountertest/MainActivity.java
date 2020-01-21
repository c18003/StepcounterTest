package com.example.stepcountertest;

import androidx.appcompat.app.AppCompatActivity;
//import android.app.Activity;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
//import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager manager;
    private Sensor stepCntSensor;
    private int stepcount2 = 0;

    private TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager = (SensorManager) getSystemService(SENSOR_SERVICE);

        stepCntSensor = manager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);

        textView2 = (TextView) findViewById(R.id.textView2);

        textView2.setText(" ");

        Button sendButton = findViewById(R.id.button);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(),SubActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        Sensor sensor = event.sensor;
        float[] values = event.values;
        long timestamp = event.timestamp;


        if (sensor.getType() == sensor.TYPE_STEP_COUNTER) {
            Log.d("type_step_counter", String.valueOf(values[0]));
            stepcount2++;
            textView2.setText(" " + stepcount2 + " /歩");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        manager.registerListener(this, stepCntSensor, SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    protected void onPause() {
        super.onPause();

        manager.unregisterListener(this, stepCntSensor);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mokuhyou:
                // ボタンをタップした際の処理を記述
                break;
        }
        return true;
    }
}
