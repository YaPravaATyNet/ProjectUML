package com.example.projectuml;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.projectuml.db.DatabaseHelper;

public class MainActivity extends AppCompatActivity {

   // public static final String EXTRA_MESSAGE = "UNIT_TYPE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickLearning(View v) {
        Intent intention = new Intent(v.getContext(), Learning.class);
        startActivity(intention);
    }

    public void onClickTarget(View v) {
        Intent intention = new Intent(v.getContext(), TargetList.class);
        startActivity(intention);
    }

    public void onClickStatistics(View v) {
        Intent intention = new Intent(v.getContext(), Statistics.class);
        startActivity(intention);
    }

    public void onClickHelp(View v) {
        Intent intention = new Intent(v.getContext(), Help.class);
        startActivity(intention);
    }

    public void onClickFeedback(View v) {
        Intent intention = new Intent(v.getContext(), Feedback.class);
        startActivity(intention);
    }

}
