package com.example.myapplication;

public class Item {
    public String name;
    public float price;
    public int amount;
    public Boolean isFavourite;
    public int imgResId;
    public Item(String name, float price, int imgResId){
        this.name = name;
        this.price = price;
        this.isFavourite = false;
        this.amount = 0;
        this.imgResId = imgResId;
    }

}
