package com.example.projectuml.view;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectuml.db.DatabaseHelper;
import com.example.projectuml.R;
import com.example.projectuml.model.Checkable;
import com.example.projectuml.model.Task;

import java.util.List;

public class Unit extends AppCompatActivity implements OnFragmentInteractionListener {

    private String UNIT_NAME;
    private int current;
    private Fragment currentFragment = null;

    public static String QUESTION = "QUESTION";
    public static String ANSWER = "ANSWER";

    TextView unitName;
    Button next;
    Button check;

    DatabaseHelper dbHelper;
    List<Task> tasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit);

        next = findViewById(R.id.next);
        check = findViewById(R.id.check);

        Intent intent = getIntent();
        UNIT_NAME = intent.getStringExtra(Unit_List.UNIT_NAME_TAG);

        unitName = findViewById(R.id.unitName);
        unitName.setText(UNIT_NAME);

        dbHelper = new DatabaseHelper(getApplicationContext());
        tasks = dbHelper.getAllTasksByUnit(dbHelper.getReadableDatabase(), UNIT_NAME);

        showNextFragment();

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNextFragment();
            }
        });

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentFragment != null) {
                    Toast.makeText(getApplicationContext(),
                            ((Checkable) currentFragment).check() ? "Correct" : "Incorrect", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void showNextFragment() {
        if (current != tasks.size()) {
            Bundle bundle = new Bundle();
            bundle.putString(QUESTION, tasks.get(current).getQuestion());
            bundle.putString(ANSWER, tasks.get(current).getAnswer());
            Fragment fr = null;
            try {
                fr = ((Fragment) tasks.get(current).getCls().newInstance());
                fr.setArguments(bundle);
            } catch (IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }
            if (currentFragment == null) {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.container, fr)
                        .commit();
                currentFragment = fr;
            } else {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, fr)
                        .commit();
                currentFragment = fr;
            }
            current++;
        } else {
            finish();
        }
    }

    @Override
    public void onFragmentInteraction(boolean correct) {
        Toast.makeText(getApplicationContext(),
                correct ? "Correct" : "Incorrect", Toast.LENGTH_SHORT).show();
    }
}
