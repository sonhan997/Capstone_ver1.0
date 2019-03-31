package com.example.nguye.capston1_dtu.Adapter_controller;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nguye.capston1_dtu.Model.Anh;
import com.example.nguye.capston1_dtu.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AnhAdapter extends RecyclerView.Adapter<AnhAdapter.ViewHolder> {

    Context context;
    ArrayList<Anh> listAnh;

    public AnhAdapter(Context context,ArrayList<Anh> listAnh){
        this.context = context;
        this.listAnh = listAnh;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.anh_items,viewGroup,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Picasso.get().load(listAnh.get(i).getImage()).into(viewHolder.imageView);
        viewHolder.title.setText(listAnh.get(i).getTitle());
    }

    @Override
    public int getItemCount() {
        return listAnh.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.mTextView);
            imageView = itemView.findViewById(R.id.mImageV);
        }
    }
}
