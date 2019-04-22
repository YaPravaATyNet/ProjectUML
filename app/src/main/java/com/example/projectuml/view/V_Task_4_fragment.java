package com.example.projectuml.view;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

import com.example.projectuml.R;
import com.example.projectuml.model.Checkable;

public class V_Task_4_fragment extends Fragment implements Checkable {

    private OnFragmentInteractionListener mListener;
    private EditText editText;
    private MediaPlayer mediaPlayer;
    private Button buttonPlayStop;
    private SeekBar seekBar;
    private Button check;

    private final Handler handler = new Handler();

    public V_Task_4_fragment() {
        // Required empty public constructor
    }

    private void seekChange(View v) {
        if (mediaPlayer.isPlaying()) {
            SeekBar sb = (SeekBar) v;
            mediaPlayer.seekTo(sb.getProgress());
        }
    }

    public void startPlayProgressUpdater() {
        seekBar.setProgress(mediaPlayer.getCurrentPosition());

        if (mediaPlayer.isPlaying()) {
            Runnable notification = new Runnable() {
                public void run() {
                    startPlayProgressUpdater();
                }
            };
            handler.postDelayed(notification, 1000);
        } else {
            mediaPlayer.pause();
            buttonPlayStop.setText("Start");
            seekBar.setProgress(0);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_v__task_4_fragment, container, false);

        check = v.findViewById(R.id.check);
        buttonPlayStop = v.findViewById(R.id.controll);

        buttonPlayStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buttonPlayStop.getText().toString().equals("Start")) {
                    buttonPlayStop.setText("Stop");
                    try {
                        mediaPlayer.start();
                        startPlayProgressUpdater();
                    } catch (IllegalStateException e) {
                        mediaPlayer.pause();
                    }
                } else {
                    buttonPlayStop.setText("Start");
                    mediaPlayer.pause();
                }
            }
        });

        mediaPlayer = MediaPlayer.create(getContext(), R.raw.record);

        seekBar = v.findViewById(R.id.seekBar);
        seekBar.setMax(mediaPlayer.getDuration());
        seekBar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                seekChange(v);
                return false;
            }
        });

        editText = v.findViewById(R.id.answer);
        check.setOnClickListener(new View.OnClickListener() {
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
        mediaPlayer.pause();
        mListener = null;
    }

    @Override
    public boolean check() {
        return ((EditText) getView().findViewById(R.id.answer)).getText().toString().equals(getArguments().getString(Unit.ANSWER));
    }
}