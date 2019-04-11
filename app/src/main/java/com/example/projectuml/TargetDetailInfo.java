package com.example.projectuml;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TargetDetailInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_target);
        Target target = (Target) getIntent().getExtras().getSerializable("target");
        TextView name = (TextView) findViewById(R.id.name_target);
        name.setText(target.getName());
        TextView idea = (TextView) findViewById(R.id.idea_target);
        idea.setText("Выучить " + target.getIdea());
        TextView start = (TextView) findViewById(R.id.time_start);
        start.setText("Время старта: " + target.getStart().getTime());
        TextView end = (TextView) findViewById(R.id.time_end);
        GregorianCalendar date = (GregorianCalendar) target.getStart().clone();
        date.add(Calendar.DAY_OF_MONTH, target.getDays());
        date.add(Calendar.HOUR_OF_DAY, target.getHours());
        end.setText("Время окончания: " + date.getTime());
        ProgressBar progress = (ProgressBar) findViewById(R.id.progress);
        progress.setMax(target.getQuantity());
        progress.setProgress(target.getProgress());
        ProgressBar timePass = (ProgressBar) findViewById(R.id.time_pass);
        timePass.setMax(target.getDays() * 24 * 60 + target.getHours() * 60);
        Date curDate = new Date();
        long dif = (curDate.getTime() - target.getStart().getTime().getTime()) / 60000;
        timePass.setProgress((int)dif);
    }

}
