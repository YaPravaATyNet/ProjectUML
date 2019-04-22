package com.example.projectuml.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
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


public class V_Task_2_fragment extends Fragment implements Checkable {

    private OnFragmentInteractionListener mListener;

    //private int correctAnswer = 0;

    RadioButton rb_1;
    RadioButton rb_2;
    RadioButton rb_3;
    RadioButton rb_4;
    RadioGroup rg;

    DatabaseHelper dbHelper;

    public V_Task_2_fragment() {
        // Required empty public constructor
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

        String rb__1 = dbHelper.getTrash(dbHelper.getReadableDatabase(),11, 20);
        String rb__2 = dbHelper.getTrash(dbHelper.getReadableDatabase(),11, 20);
        String rb__3 = dbHelper.getTrash(dbHelper.getReadableDatabase(),11, 20);
        String rb__4 = dbHelper.getTrash(dbHelper.getReadableDatabase(),11, 20);

        rb_1.setBackground(v.getContext().getResources().getDrawable(Integer.valueOf(rb__1)));
        rb_2.setBackground(v.getContext().getResources().getDrawable(Integer.valueOf(rb__2)));
        rb_3.setBackground(v.getContext().getResources().getDrawable(Integer.valueOf(rb__3)));
        rb_4.setBackground(v.getContext().getResources().getDrawable(Integer.valueOf(rb__4)));

        rb_1.setTag(rb__1);
        rb_2.setTag(rb__2);
        rb_3.setTag(rb__3);
        rb_4.setTag(rb__4);


        int random = DatabaseHelper.getIntRandomFromRange(1, 4);
        //correctAnswer = random;

        switch (random) {
            case 1:
                rb_1.setBackground(v.getContext().getResources().getDrawable(Integer.valueOf(getArguments().getString(Unit.ANSWER))));
                rb_1.setTag(getArguments().getString(Unit.ANSWER));
                break;
            case 2:
                rb_2.setBackground(v.getContext().getResources().getDrawable(Integer.valueOf(getArguments().getString(Unit.ANSWER))));
                rb_2.setTag(getArguments().getString(Unit.ANSWER));
                break;
            case 3:
                rb_3.setBackground(v.getContext().getResources().getDrawable(Integer.valueOf(getArguments().getString(Unit.ANSWER))));
                rb_3.setTag(getArguments().getString(Unit.ANSWER));
                break;
            case 4:
                rb_4.setBackground(v.getContext().getResources().getDrawable(Integer.valueOf(getArguments().getString(Unit.ANSWER))));
                rb_4.setTag(getArguments().getString(Unit.ANSWER));
                break;
        }

        return v;
    }

    public void onButtonPressed(boolean correct) {
        if (mListener != null) {
            mListener.onFragmentInteraction(correct);
        }
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

    @Override
    public boolean check() {
        return ((RadioButton)
                getView()
                        .findViewById(
                                ((RadioGroup)
                                        getView()
                                                .findViewById(R.id.radios_type_1))
                                        .getCheckedRadioButtonId()))
                .getTag()
                .toString()
                .equals(getArguments()
                        .getString(Unit.ANSWER));
    }
}