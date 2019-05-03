package com.example.sawek.quizee.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.sawek.quizee.R;
import com.example.sawek.quizee.database.Question;
import com.example.sawek.quizee.database.QuestionAdapter;

import java.util.List;

public class QuizFragment extends Fragment {

    RecyclerView recyclerView;
    QuestionAdapter questionAdapter;
    List<Question> questionList;
    private Button next;

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.from(container.getContext()).inflate(R.layout.quiz_layout,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView=getView().findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        questionAdapter = new QuestionAdapter(getContext(),questionList);
        recyclerView.setAdapter(questionAdapter);
        next = view.findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("next ","next");
                SummaryFragment summaryFragment = new SummaryFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_holder,summaryFragment);
                fragmentTransaction.addToBackStack("back");
                fragmentTransaction.commit();
            }
        });



    }
}
