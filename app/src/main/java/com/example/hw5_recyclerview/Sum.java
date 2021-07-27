package com.example.hw5_recyclerview;

public class Sum extends RowType {

    private String title;
    private Integer sum;

    public Sum(String title, Integer sum) {
        this.title = title;
        this.sum = sum;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}