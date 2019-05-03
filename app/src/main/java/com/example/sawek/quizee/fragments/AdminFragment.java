package com.example.sawek.quizee.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.sawek.quizee.R;
import com.example.sawek.quizee.database.Question;
import com.firebase.client.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

public class AdminFragment extends Fragment {

    private EditText question;
    private EditText answer1;
    private EditText answer2;
    private EditText answer3;
    private EditText answer4;
    private EditText category;
    private EditText correctAnswer,password;
    private Button addButton,unlock;
    private DatabaseReference mDatabase;
    private Integer child;
    private String child_string;
    private Random random;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.add_question,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Firebase.setAndroidContext(getContext());
        mDatabase = FirebaseDatabase.getInstance().getReference();
        random = new Random();

        question = view.findViewById(R.id.enter_quest);
        answer1 = view.findViewById(R.id.answer1);
        answer2 = view.findViewById(R.id.answer2);
        answer3 = view.findViewById(R.id.answer3);
        answer4 = view.findViewById(R.id.answer4);
        category = view.findViewById(R.id.category);
        correctAnswer = view.findViewById(R.id.correct_answer);
        addButton = view.findViewById(R.id.button_save);
        password = view.findViewById(R.id.password);
        unlock = view.findViewById(R.id.unlock);

        makeGone();
        unlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (password.getText().toString().equals("qwerty123")){
                    makeVisible();
                }
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Question question1 = new Question(question.getText().toString(),answer1.getText().toString(),
                        answer2.getText().toString(), answer3.getText().toString(),answer4.getText().toString(),
                        category.getText().toString(),correctAnswer.getText().toString());

               child = random.nextInt(10000);
               child_string = child.toString();
               mDatabase.child(child_string).setValue(question1);
            }
        });
    }

    private void makeGone(){
        question.setVisibility(View.GONE);
        answer1.setVisibility(View.GONE);
        answer2.setVisibility(View.GONE);
        answer3.setVisibility(View.GONE);
        answer4.setVisibility(View.GONE);
        correctAnswer.setVisibility(View.GONE);
        category.setVisibility(View.GONE);
        addButton.setVisibility(View.GONE);
    }
    private void makeVisible(){
        question.setVisibility(View.VISIBLE);
        answer1.setVisibility(View.VISIBLE);
        answer2.setVisibility(View.VISIBLE);
        answer3.setVisibility(View.VISIBLE);
        answer4.setVisibility(View.VISIBLE);
        correctAnswer.setVisibility(View.VISIBLE);
        category.setVisibility(View.VISIBLE);
        addButton.setVisibility(View.VISIBLE);
    }
}
