package com.example.hw5_recyclerview;

public class Apple extends RowType {

    private String title;
    public Integer basketId;

    public Apple(String title, Integer id, Integer basketId) {
        this.title = title;
        this.id = id;
        this.basketId = basketId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
