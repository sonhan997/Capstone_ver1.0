package com.example.nguye.capston1_dtu.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.nguye.capston1_dtu.Adapter_controller.MonHocAdapter;
import com.example.nguye.capston1_dtu.Model.MonHoc;
import com.example.nguye.capston1_dtu.R;
import com.example.nguye.capston1_dtu.common.setOnItemClick;

import java.util.ArrayList;

public class BaiHoc extends Fragment implements setOnItemClick {
    RecyclerView mRev;
    MonHocAdapter monHocAdapter;
    public static final String MY_NAME = "Capstone";
    final ArrayList<MonHoc> listMon = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.list_bai_hoc,null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRev = view.findViewById(R.id.mRecycler_V);
        mRev.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false);
        mRev.setLayoutManager(manager);
        
        inView();
    }

    private void inView() {
        listMon.add(new MonHoc(R.drawable.logodtu,"Toán đề số 1"));
        listMon.add(new MonHoc(R.drawable.logodtu,"Toán đề số 2"));
        listMon.add(new MonHoc(R.drawable.logodtu,"Toán đề số 3"));
        listMon.add(new MonHoc(R.drawable.logodtu,"Toán đề số 4"));
        listMon.add(new MonHoc(R.drawable.logodtu,"Toán đề số 5"));
        listMon.add(new MonHoc(R.drawable.logodtu,"Toán đề số 6"));
        listMon.add(new MonHoc(R.drawable.logodtu,"Toán đề số 7"));
        listMon.add(new MonHoc(R.drawable.logodtu,"Toán đề số 8"));
        listMon.add(new MonHoc(R.drawable.logodtu,"Toán đề số 9"));

        monHocAdapter = new MonHocAdapter(listMon,getActivity());
        mRev.setAdapter(monHocAdapter);
        monHocAdapter.setOnClickListener(BaiHoc.this);

    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(getActivity(),List_Image_Bai_Hoc.class);
        MonHoc monHoc = listMon.get(position);
        intent.putExtra(MY_NAME,monHoc.getName());
        startActivity(intent);
    }
}
