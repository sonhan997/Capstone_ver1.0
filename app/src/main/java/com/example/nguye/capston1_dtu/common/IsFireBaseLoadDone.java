package com.example.nguye.capston1_dtu.common;

import com.example.nguye.capston1_dtu.Model.Question;

import java.util.ArrayList;

public interface IsFireBaseLoadDone {
    void onFirebaseLoadSuccess(ArrayList<Question> arrayList);

    void onFirebaseLoadFailed(String mess);
}
