package com.example.projectuml;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ListTarget extends AppCompatActivity {

    ArrayList<Target> targets = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_target);

        fillTargets();
        ArrayAdapter<Target> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, targets);
        ListView targetList = (ListView) findViewById(R.id.target_list);
        targetList.setAdapter(adapter);
        targetList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intention = new Intent(view.getContext(), TargetDetailInfo.class);
                intention.putExtra("target", targets.get(position));
                startActivity(intention);
            }
        });

    }

    private void fillTargets() {
        Target target = new Target("Raf", TargetType.UNIT, 2, 10, 2, 1, TargetState.NOT_FINISHED);
        Target target1 = new Target( "Sasha", TargetType.WORDS, 10, 1, 0, 2, TargetState.NOT_FINISHED);
        targets.add(target);
        targets.add(target1);
    }

    public void createNewTarget(View v) {
        Intent intention = new Intent(v.getContext(), TargetCreation.class);
        startActivity(intention);
    }
}
