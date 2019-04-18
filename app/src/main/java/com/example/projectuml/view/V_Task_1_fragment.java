package com.example.projectuml.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.projectuml.R;
import com.example.projectuml.db.DatabaseHelper;
import com.example.projectuml.model.Checkable;

public class V_Task_1_fragment extends Fragment implements Checkable {

    private OnFragmentInteractionListener mListener;

    private int correctAnswer = 0;

    RadioButton rb_1;
    RadioButton rb_2;
    RadioButton rb_3;
    RadioButton rb_4;
    RadioGroup rg;

    DatabaseHelper dbHelper;

    public V_Task_1_fragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_v__task_1_fragment, container, false);

        TextView tv = v.findViewById(R.id.question);
        tv.setText(getArguments().getString(Unit.QUESTION));

        dbHelper = new DatabaseHelper(getContext());

        rg = v.findViewById(R.id.radios_type_1);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (check()){
                    dbHelper.updateTaskState(dbHelper.getReadableDatabase(), getArguments().getString(Unit.QUESTION), getArguments().getString(Unit.ANSWER));
                }
                onButtonPressed(check());
            }
        });

        rb_1 = v.findViewById(R.id.first_var);
        rb_2 = v.findViewById(R.id.second_var);
        rb_3 = v.findViewById(R.id.third_var);
        rb_4 = v.findViewById(R.id.fourth_var);

        rb_1.setText(dbHelper.getTrash(dbHelper.getReadableDatabase()));
        rb_2.setText(dbHelper.getTrash(dbHelper.getReadableDatabase()));
        rb_3.setText(dbHelper.getTrash(dbHelper.getReadableDatabase()));
        rb_4.setText(dbHelper.getTrash(dbHelper.getReadableDatabase()));

        int random = DatabaseHelper.getIntRandomFromRange(1, 4);
        correctAnswer = random;

        switch (random) {
            case 1:
                rb_1.setText(getArguments().getString(Unit.ANSWER));
                break;
            case 2:
                rb_2.setText(getArguments().getString(Unit.ANSWER));
                break;
            case 3:
                rb_3.setText(getArguments().getString(Unit.ANSWER));
                break;
            case 4:
                rb_4.setText(getArguments().getString(Unit.ANSWER));
                break;
        }

        return v;
    }

    public void onButtonPressed(boolean correct) {
        if (mListener != null) {
            mListener.onFragmentInteraction(correct);
        }
    }

    public boolean check() {
        return ((RadioButton)
                getView()
                    .findViewById(
                            ((RadioGroup)
                                    getView()
                                        .findViewById(R.id.radios_type_1))
                            .getCheckedRadioButtonId()))
                .getText()
                .toString()
                .equals(getArguments()
                        .getString(Unit.ANSWER));
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}
