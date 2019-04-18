package com.example.projectuml;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.projectuml.view.Unit_List;

public class Learning extends AppCompatActivity {

    private static final String EXTRA_MESSAGE = "UNIT_TYPE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning);
    }

    public void onClickGrammar(View v) {
        Intent intent = new Intent(getApplicationContext(), Unit_List.class);
        intent.putExtra(EXTRA_MESSAGE, 0);
        startActivity(intent);
    }

    public void  onClickVocabulary(View v) {
        Intent intent = new Intent(getApplicationContext(), Unit_List.class);
        intent.putExtra(EXTRA_MESSAGE, 1);
        startActivity(intent);
    }


}
