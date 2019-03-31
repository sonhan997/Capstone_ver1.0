package com.example.nguye.capston1_dtu.Adapter_controller;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.nguye.capston1_dtu.Model.Exam;
import com.example.nguye.capston1_dtu.R;

import java.util.ArrayList;

public class ExamAdapter extends ArrayAdapter<Exam> {
    public ExamAdapter(@NonNull Context context, ArrayList<Exam> exams) {
        super(context,0, exams);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView==null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.items_ten_de_thi,parent,false);
        }
        TextView textView = convertView.findViewById(R.id.title);
        ImageView imageView = convertView.findViewById(R.id.imageLOGO);

        Exam exam = getItem(position);
        if (exam !=null){
            textView.setText("" + exam.getName());
            imageView.setImageResource(R.drawable.subject);
        }
        return convertView;
    }
}
