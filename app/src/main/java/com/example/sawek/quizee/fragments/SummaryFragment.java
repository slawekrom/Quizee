package com.example.sawek.quizee.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.sawek.quizee.InfoData;
import com.example.sawek.quizee.R;

public class SummaryFragment extends Fragment {
    private TextView correctAnswer,badAnswer,earnedPoints;
    private InfoData infoData;
    private Button ngButton,menuButton;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.summary,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        infoData=InfoData.getInstance();
        correctAnswer=view.findViewById(R.id.correct_answer_number);
        badAnswer=view.findViewById(R.id.bad_answer_number);
        earnedPoints=view.findViewById(R.id.earned_points_number);
        correctAnswer.setText(String.valueOf(infoData.getGoodAnswer()));
        badAnswer.setText(String.valueOf(infoData.getBadAnswer()));
        earnedPoints.setText(String.valueOf(infoData.getGoodAnswer()*50));
        ngButton = view.findViewById(R.id.ng_button);
        menuButton = view.findViewById(R.id.menu_button_summary);

        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                FragmentMenu fragmentMenu = new FragmentMenu();
                fragmentTransaction.replace(R.id.fragment_holder,fragmentMenu);
                fragmentTransaction.commit();
            }
        });
        ngButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                NewGameFragment newGameFragment = new NewGameFragment();
                fragmentTransaction.replace(R.id.fragment_holder,newGameFragment);
                fragmentTransaction.addToBackStack("back");
                fragmentTransaction.commit();
            }
        });


    }
}
