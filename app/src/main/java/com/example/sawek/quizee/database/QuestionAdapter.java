package com.example.sawek.quizee.database;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sawek.quizee.InfoData;
import com.example.sawek.quizee.R;
import com.example.sawek.quizee.fragments.GetPointsfragment;
import com.example.sawek.quizee.fragments.SummaryFragment;

import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder>{

    private Context mContext;
    private List<Question> mQuestions;
    private InfoData infoData;
    private String label;

    public QuestionAdapter(Context context,List<Question> mQuestions) {
        this.mContext=context;
        this.mQuestions=mQuestions;
    }

    @NonNull
    @Override
    public QuestionAdapter.QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View itemView = inflater.inflate(R.layout.question_list_item, null);
        infoData=InfoData.getInstance();
        return new QuestionViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final QuestionAdapter.QuestionViewHolder holder, int position) {
        final Question current = mQuestions.get(position);
        if (mQuestions != null) {
            holder.QuestionItemView.setText(current.getQuestion());
            holder.ItemAnswer1.setText(current.getAnswer1());
            holder.ItemAnswer2.setText(current.getAnswer2());
            holder.ItemAnswer3.setText(current.getAnswer3());
            holder.ItemAnswer4.setText(current.getAnswer4());

        } else {
            // Covers the case of data not being ready yet.
            Log.i("loading question fail ","questions cannot be load ");
            Toast.makeText(mContext,"Questions cannot load ",Toast.LENGTH_LONG).show();
        }
        holder.ItemAnswer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.ItemAnswer4.getText().toString().equals(current.getCorrectAnswer())){
                    holder.ItemAnswer4.setTextColor(mContext.getResources().getColor(R.color.correct_answer));
                    infoData.setPoints(infoData.getPoints()+50);
                    infoData.setGoodAnswer(infoData.getGoodAnswer()+1);
                }
                if(! holder.ItemAnswer4.getText().toString().equals(current.getCorrectAnswer())){
                    holder.ItemAnswer4.setTextColor(mContext.getResources().getColor(R.color.bad_answer));
                    infoData.setBadAnswer(infoData.getBadAnswer()+1);
                }
                holder.ItemAnswer1.setEnabled(false);
                holder.ItemAnswer2.setEnabled(false);
                holder.ItemAnswer3.setEnabled(false);
                holder.ItemAnswer4.setEnabled(false);
            }
        });
        holder.ItemAnswer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.ItemAnswer1.getText().toString().equals(current.getCorrectAnswer())){
                    holder.ItemAnswer1.setTextColor(mContext.getResources().getColor(R.color.correct_answer));
                    infoData.setPoints(infoData.getPoints()+50);
                    infoData.setGoodAnswer(infoData.getGoodAnswer()+1);
                }
                if(! holder.ItemAnswer1.getText().toString().equals(current.getCorrectAnswer())){
                    holder.ItemAnswer1.setTextColor(mContext.getResources().getColor(R.color.bad_answer));
                    infoData.setBadAnswer(infoData.getBadAnswer()+1);
                }
                holder.ItemAnswer1.setEnabled(false);
                holder.ItemAnswer2.setEnabled(false);
                holder.ItemAnswer3.setEnabled(false);
                holder.ItemAnswer4.setEnabled(false);
            }
        });
        holder.ItemAnswer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.ItemAnswer2.getText().toString().equals(current.getCorrectAnswer())){
                    holder.ItemAnswer2.setTextColor(mContext.getResources().getColor(R.color.correct_answer));
                    infoData.setPoints(infoData.getPoints()+50);
                    infoData.setGoodAnswer(infoData.getGoodAnswer()+1);
                }
                if(! holder.ItemAnswer2.getText().toString().equals(current.getCorrectAnswer())){
                    holder.ItemAnswer2.setTextColor(mContext.getResources().getColor(R.color.bad_answer));
                    infoData.setBadAnswer(infoData.getBadAnswer()+1);
                }
                holder.ItemAnswer1.setEnabled(false);
                holder.ItemAnswer2.setEnabled(false);
                holder.ItemAnswer3.setEnabled(false);
                holder.ItemAnswer4.setEnabled(false);
            }
        });
        holder.ItemAnswer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.ItemAnswer3.getText().toString().equals(current.getCorrectAnswer())){
                    holder.ItemAnswer3.setTextColor(mContext.getResources().getColor(R.color.correct_answer));
                    infoData.setPoints(infoData.getPoints()+50);
                    infoData.setGoodAnswer(infoData.getGoodAnswer()+1);

                }
                if(! holder.ItemAnswer3.getText().toString().equals(current.getCorrectAnswer())){
                    holder.ItemAnswer3.setTextColor(mContext.getResources().getColor(R.color.bad_answer));
                    infoData.setBadAnswer(infoData.getBadAnswer()+1);
                }
                holder.ItemAnswer1.setEnabled(false);
                holder.ItemAnswer2.setEnabled(false);
                holder.ItemAnswer3.setEnabled(false);
                holder.ItemAnswer4.setEnabled(false);
            }
        });
        holder.buyAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(InfoData.getInstance().getPoints()>=500){
                    InfoData.getInstance().setPoints(InfoData.getInstance().getPoints()-500);

                    if(holder.ItemAnswer1.getText().toString().equals(current.getCorrectAnswer())){
                        holder.ItemAnswer1.setTextColor(mContext.getResources().getColor(R.color.yellow));
                    }
                    if(holder.ItemAnswer2.getText().toString().equals(current.getCorrectAnswer())){
                        holder.ItemAnswer2.setTextColor(mContext.getResources().getColor(R.color.yellow));
                    }
                    if(holder.ItemAnswer3.getText().toString().equals(current.getCorrectAnswer())){
                        holder.ItemAnswer3.setTextColor(mContext.getResources().getColor(R.color.yellow));
                    }
                    if(holder.ItemAnswer4.getText().toString().equals(current.getCorrectAnswer())){
                        holder.ItemAnswer4.setTextColor(mContext.getResources().getColor(R.color.yellow));
                    }
                }
                else{
                    label = mContext.getResources().getString(R.string.bonus_info);
                    Toast.makeText(mContext, label, Toast.LENGTH_SHORT).show();}
            }
        });
    }

    void setQuestions(List<Question> questions){
        mQuestions = questions;
        //notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        if (mQuestions != null)
            return mQuestions.size();
        else return 0;
    }

    public class QuestionViewHolder extends RecyclerView.ViewHolder {
        private final TextView QuestionItemView;
        private final Button ItemAnswer1;
        private final Button ItemAnswer2;
        private final Button ItemAnswer3;
        private final Button ItemAnswer4;
        private final Button buyAnswer;
        public QuestionViewHolder(@NonNull View itemView) {
            super(itemView);
            QuestionItemView = itemView.findViewById(R.id.the_question);
            ItemAnswer1 = itemView.findViewById(R.id.answerButton1);
            ItemAnswer2 = itemView.findViewById(R.id.answerButton2);
            ItemAnswer3 = itemView.findViewById(R.id.answerButton3);
            ItemAnswer4 = itemView.findViewById(R.id.answerButton4);
            buyAnswer = itemView.findViewById(R.id.buyAnswer);
        }
    }
}
