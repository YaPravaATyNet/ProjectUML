package com.example.projectuml.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.projectuml.R;
import com.example.projectuml.db.DatabaseHelper;

public class Statistics extends AppCompatActivity {
    int word;
    int grammar;
    int vocabulary;
    int unit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());

        word = databaseHelper.getReadyWords();
        grammar = databaseHelper.getReadyUnit(0);
        vocabulary = databaseHelper.getReadyUnit(1);
        unit = grammar + vocabulary;

        TextView textWord = (TextView) findViewById(R.id.st_words);
        textWord.setText("Выучено слов: " + Integer.toString(word));
        TextView textGrammar = (TextView) findViewById(R.id.st_grammar);
        textGrammar.setText("Пройдено грамматических юнитов: " + Integer.toString(grammar));
        TextView textVocabulary = (TextView) findViewById(R.id.st_lex);
        textVocabulary.setText("Пройденно лексических юнитов: " + Integer.toString(vocabulary));
        TextView textUnit = (TextView) findViewById(R.id.st_unit);
        textUnit.setText("Пройдено всего юнитов: " + Integer.toString(unit));
    }
}
