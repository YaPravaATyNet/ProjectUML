package com.example.projectuml.view;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projectuml.R;
import com.example.projectuml.model.Checkable;


public class GR_Task_4_fragment extends Fragment implements Checkable {

    private OnFragmentInteractionListener mListener;

    public GR_Task_4_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gr__task_4_fragment, container, false);
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
        return false;
    }
}
