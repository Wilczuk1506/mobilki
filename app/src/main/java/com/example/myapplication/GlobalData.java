package com.example.myapplication;

import android.widget.Toast;

import java.util.ArrayList;

public class GlobalData {
    public static GlobalData instance;
    public ArrayList<Item> storeItems;
    public ArrayList<Item> cartItems;

    private GlobalData(){
        storeItems = new ArrayList<>();
        storeItems.add(new Item("Butter", 20.01f, R.drawable.img1 ));
        storeItems.add(new Item("Beer", 4.79f, R.drawable.img2));
        storeItems.add(new Item("Matchsticks", 1.22f, R.drawable.img3));
        storeItems.add(new Item("Diuna ice-cream cone", 1.29f, R.drawable.img4));
        storeItems.add(new Item("Green tea", 4.59f, R.drawable.img5));
        storeItems.add(new Item("Dish soap", 8.03f, R.drawable.img6));
        storeItems.add(new Item("Mascara", 39.99f, R.drawable.img7));
        storeItems.add(new Item("Coconut dog spray", 0.17f, R.drawable.img8));
        storeItems.add(new Item("Smoked water", 999.89f, R.drawable.img9));
        storeItems.add(new Item("Ostrich spring rolls", 14.99f, R.drawable.img10));

        cartItems = new ArrayList<>();
    }
    public static GlobalData getInstance(){
        if (instance == null){
            instance = new GlobalData();
        }
        return instance;
    }

    public void addItemToCart(int position){
        Item addingItem = storeItems.get(position);

        int inCartIndex = cartItems.indexOf(addingItem);

        if (inCartIndex == -1){ //is not in
            addingItem.amount = 1;
            cartItems.add(addingItem);
        }
        else { // is in
            cartItems.get(inCartIndex).amount++;
        }
    }
}
