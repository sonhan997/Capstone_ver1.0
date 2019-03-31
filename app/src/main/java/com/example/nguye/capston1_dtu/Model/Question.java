package com.example.nguye.capston1_dtu.Model;

import java.io.Serializable;

public class Question implements Serializable {
    private String title;
    private String viewQuestion;
    private String questionA;
    private String questionB;
    private String questionC;
    private String questionD;
    private String dapAn;
    private String tamAns;
    public int checkID = -0; // mac dịnh là không chọn
    // private int count =0;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Question(String title, String viewQuestion, String questionA, String questionB, String questionC, String questionD, String dapAn, String tamAns) {
        this.title = title;
        this.viewQuestion = viewQuestion;
        this.questionA = questionA;
        this.questionB = questionB;
        this.questionC = questionC;
        this.questionD = questionD;
        this.dapAn = dapAn;
        this.tamAns = tamAns;
        //  this.count = count;
    }

    public Question() {
    }

    /*public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
*/
    public String getViewQuestion() {
        return viewQuestion;
    }

    public void setViewQuestion(String viewQuestion) {
        this.viewQuestion = viewQuestion;
    }

    public String getQuestionA() {
        return questionA;
    }

    public void setQuestionA(String questionA) {
        this.questionA = questionA;
    }

    public String getQuestionB() {
        return questionB;
    }

    public void setQuestionB(String questionB) {
        this.questionB = questionB;
    }

    public String getQuestionC() {
        return questionC;
    }

    public void setQuestionC(String questionC) {
        this.questionC = questionC;
    }

    public String getQuestionD() {
        return questionD;
    }

    public void setQuestionD(String questionD) {
        this.questionD = questionD;
    }

    public String getDapAn() {
        return dapAn;
    }

    public void setDapAn(String dapAn) {
        this.dapAn = dapAn;
    }

    public String getTamAns() {
        return tamAns;
    }

    public void setTamAns(String tamAns) {
        this.tamAns = tamAns;
    }
}
