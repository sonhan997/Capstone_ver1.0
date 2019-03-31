package com.example.nguye.capston1_dtu.Adapter_controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.nguye.capston1_dtu.Model.Question;
import com.example.nguye.capston1_dtu.R;

import java.util.ArrayList;

public class CheckAnswer extends BaseAdapter {
    LayoutInflater inflater;
    ArrayList arrayList;

    public CheckAnswer(Context context, ArrayList arrayList) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Question question = (Question) getItem(position);
        ViewHolder holder;
        if (convertView == null){
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.items_griview_answer,null);
            holder.tvNumAnswer = convertView.findViewById(R.id.tvNumAnswer);
            holder.tvCheck = convertView.findViewById(R.id.tvAnswer);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        int i = position +1;
        holder.tvNumAnswer.setText("Câu "+i+": ");
        holder.tvCheck.setText(question.getTamAns());
        return convertView;
    }

    public static class ViewHolder{
        TextView tvNumAnswer, tvCheck;

    }
}
