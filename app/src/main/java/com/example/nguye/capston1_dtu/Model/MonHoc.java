package com.example.nguye.capston1_dtu.Model;

public class MonHoc {
    private int view;
    private String name;

    public MonHoc(int view, String name) {
        this.view = view;
        this.name = name;
    }

    public MonHoc() {
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
