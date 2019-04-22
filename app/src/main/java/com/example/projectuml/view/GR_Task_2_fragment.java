package com.example.projectuml.view;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.projectuml.R;
import com.example.projectuml.model.Checkable;

public class GR_Task_2_fragment extends Fragment implements Checkable {

    private OnFragmentInteractionListener mListener;

    public GR_Task_2_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_gr__task_2_fragment, container, false);
        ((TextView) v.findViewById(R.id.question)).setText(getArguments().getString(Unit.QUESTION));

        ((Button) v.findViewById(R.id.check)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonPressed(check());
            }
        });
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
        return ((EditText) getView().findViewById(R.id.answer)).getText().toString().equals(getArguments().getString(Unit.ANSWER));
    }
}