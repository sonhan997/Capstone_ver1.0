package com.example.nguye.capston1_dtu.Adapter_controller;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nguye.capston1_dtu.Model.MonHoc;
import com.example.nguye.capston1_dtu.R;
import com.example.nguye.capston1_dtu.common.setOnItemClick;

import java.util.ArrayList;

public class MonHocAdapter extends RecyclerView.Adapter<MonHocAdapter.ViewHolder>{
    ArrayList<MonHoc> listMonHoc;
    Context context;
    private setOnItemClick listener;

    public void setOnClickListener(setOnItemClick listener){
        this.listener = listener;
    }


    @NonNull
    @Override
    public MonHocAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.l_mon_hoc,viewGroup,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MonHocAdapter.ViewHolder viewHolder, int i) {
        viewHolder.imageView.setImageResource(listMonHoc.get(i).getView());
        viewHolder.textView.setText(listMonHoc.get(i).getName());
    }

    @Override
    public int getItemCount() {
        return listMonHoc.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView ;
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view_mon_hoc);
            textView = itemView.findViewById(R.id.tv_mon_hoc);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null){
                        int position = getAdapterPosition();
                        if(position!= RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    public MonHocAdapter(ArrayList<MonHoc> listMonHoc, Context context) {
        this.listMonHoc = listMonHoc;
        this.context = context;
    }
}
