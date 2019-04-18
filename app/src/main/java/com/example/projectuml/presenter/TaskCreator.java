package com.example.projectuml.presenter;

import android.database.Cursor;

import com.example.projectuml.model.Task;
import com.example.projectuml.view.GR_Task_1_fragment;
import com.example.projectuml.view.GR_Task_2_fragment;
import com.example.projectuml.view.GR_Task_3_fragment;
import com.example.projectuml.view.GR_Task_4_fragment;
import com.example.projectuml.view.V_Task_1_fragment;
import com.example.projectuml.view.V_Task_2_fragment;
import com.example.projectuml.view.V_Task_3_fragment;
import com.example.projectuml.view.V_Task_4_fragment;

public class TaskCreator {
    public static Task createTask(Cursor csr) {
        switch (csr.getInt(3)) {
            case (1):
                return new Task(csr.getString(4), csr.getString(5), GR_Task_1_fragment.class);
            case (2):
                return new Task(csr.getString(4), csr.getString(5), GR_Task_2_fragment.class);
            case (3):
                return new Task(csr.getString(4), csr.getString(5), GR_Task_3_fragment.class);
            case (4):
                return new Task(csr.getString(4), csr.getString(5), GR_Task_4_fragment.class);
            case (5):
                return new Task(csr.getString(4), csr.getString(5), V_Task_1_fragment.class);
            case (6):
                return new Task(csr.getString(4), csr.getString(5), V_Task_2_fragment.class);
            case (7):
                return new Task(csr.getString(4), csr.getString(5), V_Task_3_fragment.class);
            case (8):
                return new Task(csr.getString(4), csr.getString(5), V_Task_4_fragment.class);
            default:
                return null;
        }
    }
}
