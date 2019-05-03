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

import com.example.sawek.quizee.R;

public class FragmentMenu  extends Fragment {

    private Button newGameButton,getPointsButton,addButton;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.menu_fragment,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        newGameButton=view.findViewById(R.id.new_game_button);
        newGameButton.setOnClickListener(new View.OnClickListener() {
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
        getPointsButton=view.findViewById(R.id.get_points_button);
        getPointsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                GetPointsfragment getPointsfragment = new GetPointsfragment();
                fragmentTransaction.replace(R.id.fragment_holder,getPointsfragment);
                fragmentTransaction.addToBackStack("back");
                fragmentTransaction.commit();
            }
        });

        addButton = view.findViewById(R.id.add_question);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                AdminFragment adminFragment = new AdminFragment();
                fragmentTransaction.replace(R.id.fragment_holder,adminFragment);
                fragmentTransaction.addToBackStack("back");
                fragmentTransaction.commit();
            }
        });

    }
}
