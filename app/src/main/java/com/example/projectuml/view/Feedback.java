package com.example.projectuml.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.Toast;

import com.example.projectuml.R;

public class Feedback extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
    }

    public void send(View v) {
        Toast.makeText(v.getContext(), "Отзыв отправлен", Toast.LENGTH_LONG).show();
        finish();
    }
}
