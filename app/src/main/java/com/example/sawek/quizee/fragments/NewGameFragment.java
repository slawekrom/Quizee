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
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sawek.quizee.InfoData;
import com.example.sawek.quizee.R;
import com.example.sawek.quizee.database.Question;
import com.example.sawek.quizee.database.QuestionDAO;
import com.example.sawek.quizee.database.QuestionRoomDatabase;
import com.firebase.client.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NewGameFragment extends Fragment implements SensorEventListener {
    private static final int SHAKE_THRESHOLD = 125;
    private int tmp,size;
    private Random random;
    private InfoData infoData;
    private String selectedCategory,label;
    private float x=0;
    private float y=0;
    private float z=0;
    private float last_x=0;
    private float last_y=0;
    private float last_z=0;
    private long lastUpdate = 0;
    private SensorManager sensorManager;
    private Sensor accelerometer;
    private QuestionDAO mDAO;
    private List<Question> questionList;
    private DatabaseReference mDatabase;
    private String question,a1,a2,a3,a4,category,correct;
    private QuizFragment quizFragment;
    private TextView points;
    private Button draw;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.new_game_fragment,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        QuestionRoomDatabase db = QuestionRoomDatabase.getDatabase(getActivity().getApplication());
        mDAO = db.questionDAO();
        infoData=InfoData.getInstance();
        random = new Random();
        Firebase.setAndroidContext(getContext());
        mDatabase = FirebaseDatabase.getInstance().getReference();
        questionList = new ArrayList<>();
        quizFragment = new QuizFragment();
        points = view.findViewById(R.id.your_points_number);
        points.setText(String.valueOf(InfoData.getInstance().getPoints()));
        draw = view.findViewById(R.id.draw_button);
        draw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                randomCategory();
                loadFromWeb();
            }
        });

    }

    @Override
    public void onSensorChanged(SensorEvent event) {

            long curTime = System.currentTimeMillis();
            // only allow one update every 100ms.
            if ((curTime - lastUpdate) > 500) {
                long diffTime = (curTime - lastUpdate);
                lastUpdate = curTime;

                x = event.values[0];
                y = event.values[1];
                z = event.values[2];

                float speed = Math.abs(x+y+z - last_x - last_y - last_z) / diffTime * 10000;

                if (speed > SHAKE_THRESHOLD) {
                    Log.d("sensor", "shake detected  speed: " + speed);
                    randomCategory();

//czytanie z bazy

                        loadFromWeb();
                }
                last_x = x;
                last_y = y;
                last_z = z;
            }

    }

    private void loadFromWeb(){
        questionList.clear();
        Log.i("databse","load from web");
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    category=ds.getValue(Question.class).getCategory();
                    if(category.equals(selectedCategory)){
                        question=ds.getValue(Question.class).getQuestion();
                        a1=ds.getValue(Question.class).getAnswer1();
                        a2=ds.getValue(Question.class).getAnswer2();
                        a3=ds.getValue(Question.class).getAnswer3();
                        a4=ds.getValue(Question.class).getAnswer4();
                        correct=ds.getValue(Question.class).getCorrectAnswer();

                        final Question q = new Question(question,a1,a2,a3,a4,category,correct);
                        Log.i("question id",String.valueOf(q.getId()));
                        questionList.add(q);

                    }
                    Log.i("category",category);
                }
                startQuiz();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    private void startQuiz(){
        Log.i("start quiz","quiz alredy start");

        quizFragment.setQuestionList(questionList);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_holder,quizFragment);
        fragmentTransaction.addToBackStack("back");
        fragmentTransaction.commit();
        infoData.setBadAnswer(0);
        infoData.setGoodAnswer(0);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
    private void randomCategory(){
        size=infoData.getCategoriesList().size();
        Log.i("categories size", String.valueOf(size));
        tmp=random.nextInt(size);
        selectedCategory=infoData.getCategoriesList().get(tmp);
        questionList.clear();
        label = getContext().getResources().getString(R.string.enter_category)+" "+selectedCategory;
        Toast.makeText(getContext(), label, Toast.LENGTH_SHORT).show();
    }
    public void onResume() {
        super.onResume();
        if(sensorManager == null){
            Toast.makeText(getContext(),"Sensor not available ",Toast.LENGTH_LONG).show();
        }else {
        sensorManager.registerListener(this, accelerometer,
                SensorManager.SENSOR_DELAY_NORMAL);}
    }

    public void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

}
