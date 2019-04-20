package com.example.projectuml;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.projectuml.view.Feedback;
import com.example.projectuml.view.Help;
import com.example.projectuml.view.Learning;
import com.example.projectuml.view.Statistics;
import com.example.projectuml.view.TargetList;

public class MainActivity extends AppCompatActivity {

   //public static final String EXTRA_MESSAGE = "UNIT_TYPE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickLearning(View view) {
        Intent intention = new Intent(view.getContext(), Learning.class);
        startActivity(intention);
    }

    public void onClickTarget(View view) {
        Intent intention = new Intent(view.getContext(), TargetList.class);
        startActivity(intention);
    }

    public void onClickStatistics(View view) {
        Intent intention = new Intent(view.getContext(), Statistics.class);
        startActivity(intention);
    }

    public void onClickHelp(View view) {
        Intent intention = new Intent(view.getContext(), Help.class);
        startActivity(intention);
    }

    public void onClickFeedback(View view) {
        Intent intention = new Intent(view.getContext(), Feedback.class);
        startActivity(intention);
    }

}
