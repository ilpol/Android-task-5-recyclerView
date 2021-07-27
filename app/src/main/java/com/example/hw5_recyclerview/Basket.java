package com.example.hw5_recyclerview;

public class Basket extends RowType {

    private String title;

    public Basket(String title, Integer id) {
        this.title = title;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}

