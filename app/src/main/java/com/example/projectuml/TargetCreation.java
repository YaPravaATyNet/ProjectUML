package com.example.projectuml;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class TargetCreation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_target_creation);
        NumberPicker dayPicker = (NumberPicker) findViewById(R.id.days_picker);
        dayPicker.setMaxValue(30);
        dayPicker.setMinValue(0);
        NumberPicker hourPicker = (NumberPicker) findViewById(R.id.hour_picker);
        hourPicker.setMaxValue(23);
        hourPicker.setMinValue(0);
    }

   public void downloadTarget(View v) {
       EditText nameTarget = (EditText) findViewById(R.id.name_target);
       if (nameTarget.getText().toString().equals("")) {
           Toast.makeText(v.getContext(), "Имя цели не введено", Toast.LENGTH_SHORT).show();
           return;
       }

       RadioGroup radioGroup = (RadioGroup) findViewById(R.id.target_type);
       RadioButton radioButton = (RadioButton) findViewById(radioGroup.getCheckedRadioButtonId());
       int type = radioGroup.indexOfChild(radioButton);


       EditText quantity = (EditText) findViewById(R.id.quantity);
       if (quantity.getText().toString().equals("")) {
           Toast.makeText(v.getContext(), "Количество не введено", Toast.LENGTH_SHORT).show();
           return;
       }

       NumberPicker dayPicker = (NumberPicker) findViewById(R.id.days_picker);
       NumberPicker hourPicker = (NumberPicker) findViewById(R.id.hour_picker);
       if (dayPicker.getValue() == 0 && hourPicker.getValue() == 0) {
           Toast.makeText(v.getContext(), "Время не установлено", Toast.LENGTH_SHORT).show();
           return;
       }
    }
}
