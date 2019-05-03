package com.example.sawek.quizee.fragments;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sawek.quizee.InfoData;
import com.example.sawek.quizee.R;

public class GetPointsfragment  extends Fragment implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor stepCounter;
    private boolean isRunning;
    private TextView steps_number,points;
    private int steps,last;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.get_point_layout,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        stepCounter = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);

        steps_number = view.findViewById(R.id.steps_number);
        steps_number.setText(String.valueOf(0));
        points = view.findViewById(R.id.points_number);
        points.setText(String.valueOf(InfoData.getInstance().getPoints()));
}


    public void onResume() {
        super.onResume();
        isRunning=true;
        if(stepCounter !=null){
            sensorManager.registerListener(this,stepCounter,SensorManager.SENSOR_DELAY_GAME);
        }
        else {
            Toast.makeText(getContext(),"Sensor not available ",Toast.LENGTH_LONG).show();
        }

    }

    public void onPause() {
        super.onPause();
        isRunning = false;
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (isRunning){
            steps++;
            InfoData.getInstance().setPoints(InfoData.getInstance().getPoints()+1);
            points.setText(String.valueOf(InfoData.getInstance().getPoints()));
            Log.i("step counter ", String.valueOf(event.values[0]));
            steps_number.setText(String.valueOf(steps));
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
