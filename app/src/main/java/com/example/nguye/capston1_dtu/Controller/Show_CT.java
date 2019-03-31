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

import com.example.nguye.capston1_dtu.Adapter_controller.MonHocAdapter;
import com.example.nguye.capston1_dtu.Model.MonHoc;
import com.example.nguye.capston1_dtu.R;
import com.example.nguye.capston1_dtu.common.setOnItemClick;

import java.util.ArrayList;

public class Show_CT extends Fragment implements setOnItemClick {
    RecyclerView recyclerView;
    MonHocAdapter monHocAdapter;

    public static final String MY_NAME = "capstone";

    final ArrayList<MonHoc> arrMonHoc = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.list_cong_thuc,null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycler_mon_hoc);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);

        inView();
    }

    private void inView() {
        arrMonHoc.add(new MonHoc(R.drawable.logodtu,"Công thức Toán"));
        arrMonHoc.add(new MonHoc(R.drawable.logodtu,"Công thức Lý"));
        arrMonHoc.add(new MonHoc(R.drawable.logodtu,"Công thức Hóa"));
        arrMonHoc.add(new MonHoc(R.drawable.logodtu,"Công thức Anh Văn"));

        monHocAdapter = new MonHocAdapter(arrMonHoc,getActivity());
        recyclerView.setAdapter(monHocAdapter);
        monHocAdapter.setOnClickListener(Show_CT.this);
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(getActivity(),List_Image_cong_thuc.class);
        MonHoc monHoc = arrMonHoc.get(position);
        intent.putExtra(MY_NAME,monHoc.getName());

        startActivity(intent);
    }
}
