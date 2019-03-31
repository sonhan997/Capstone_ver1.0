package com.example.nguye.capston1_dtu.Adapter_controller;

import android.content.Context;
import android.support.annotation.NonNull;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.example.nguye.capston1_dtu.Model.Hoc;
import com.example.nguye.capston1_dtu.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BaiHocAdapter extends RecyclerView.Adapter<BaiHocAdapter.ViewHolder> {

    Context context;
    ArrayList<Hoc> arrHoc;

    public BaiHocAdapter(Context context,ArrayList<Hoc> list){
        this.context = context;
        this.arrHoc =list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.image_baihoc,viewGroup,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Picasso.get().load(arrHoc.get(i).getViewImage()).into(viewHolder.mImage);
    }

    @Override
    public int getItemCount() {
        return arrHoc.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mImage = itemView.findViewById(R.id.mImage_V_bH);
        }
    }
}
