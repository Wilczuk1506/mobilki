package com.example.quiz;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class CustomQuestionAdapter extends BaseAdapter {

    public static final int QUESTION = 0;
    public static final int SUMMARY = 1;
    Context context;
    Question[] questionList;
    LayoutInflater inflater;
    int score = 0;


    public CustomQuestionAdapter(Context ctx, Question[] questionList){
        this.context = ctx;
        this.questionList = questionList;
        inflater = LayoutInflater.from(ctx);
    }

    @Override
    public int getCount() {
        return questionList.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        Question question = questionList[position];

        if (question.GetIsQuestion()){
            return QUESTION;
        }
        else {
            return SUMMARY;
        }
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        if (getItemViewType(position) == QUESTION){
            view = inflater.inflate(R.layout.activity_quiz_list_view, null);

            Question question = questionList[position];

            TextView tvQuestion = view.findViewById(R.id.tvQuestion);
            RadioGroup radioGroup = view.findViewById(R.id.rgAnwsers);

            tvQuestion.setText(position + 1 + ". " + question.GetQuestion());

            for (int i = 0; i < question.GetAnswers().length; i++){

                final int index = i;

                RadioButton rb = new RadioButton(context);
                rb.setText(question.GetAnswers()[index]);

                if (question.GetIsAnswered()){
                    if (question.GetIsAnsweredCorrectly()){
                        view.setBackgroundColor(Color.argb(128, 0, 255, 0));
                    }
                    else {
                        view.setBackgroundColor(Color.argb(128, 255, 0, 0));
                    }

                    if (question.GetChosenAnswer() == index){
                        rb.setChecked(true);
                    }

                    rb.setEnabled(false);
                }

                final View finalView = view;
                rb.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(!question.GetIsAnswered()){
                            if(index == question.GetCorrectAnswer()) {
                                question.SetIsAnsweredCorrectly(true);
                                score++;
                            }
                            question.SetIsAnswered(true);
                            question.SetChosenAnswer(index);
                            notifyDataSetChanged();
                        }
                    }
                });

                radioGroup.addView(rb);
            }

        }
        if (getItemViewType(position) == SUMMARY){
            view = inflater.inflate(R.layout.activity_quiz_summary, null);

            TextView quizScore = view.findViewById(R.id.tvCorrectAnswers);
            Button btnReset = view.findViewById(R.id.btReset);

            quizScore.setText(score + " / " + (getCount() - 1));

            btnReset.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    score = 0;

                    for (Question question: questionList) {
                        question.SetChosenAnswer(-1);
                        question.SetIsAnsweredCorrectly(false);
                        question.SetIsAnswered(false);
                    }

                    notifyDataSetChanged();
                }
            });
        }

        return view;
    }
}
