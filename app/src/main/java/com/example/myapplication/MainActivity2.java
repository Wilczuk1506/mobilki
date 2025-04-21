package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.RadioAccessSpecifier;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;

public class MainActivity2 extends AppCompatActivity {

    private static ArrayList<Item> itemList;
    private static CartAdapter adapter;
    private ListView lv_cart;
    private Button btn_goback;
    private Button btn_pay;
    private static TextView tv_total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        itemList = GlobalData.getInstance().cartItems;

        adapter = new CartAdapter(getApplicationContext(), R.layout.cart_item, itemList);
        lv_cart = findViewById(R.id.a2_lv);
        lv_cart.setAdapter(adapter);

        btn_goback = findViewById(R.id.a2_btn_back);

        btn_goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_pay = findViewById(R.id.a2_btn_pay);

        btn_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (Item i : itemList) {
                    i.isFavourite = false;
                }
                itemList.clear(); //because of some backass logic this works again
                adapter.notifyDataSetChanged();
                updateTotal();
            }
        });

        tv_total = findViewById(R.id.a2_tv_total);
        updateTotal();
    }
    public static void removeItem(int position){
        itemList.get(position).isFavourite = false;
        itemList.remove(position); //because of some backass logic this works
        adapter.notifyDataSetChanged();
        updateTotal();
    }

    public static void modifyAmount(int position, int value){
        itemList.get(position).amount += value;
        if(itemList.get(position).amount < 0){
            itemList.get(position).amount = 0;
        }
        adapter.notifyDataSetChanged();
        updateTotal();
    }

    public static void setAmount(int position, int value){
        itemList.get(position).amount = value;
        adapter.notifyDataSetChanged();
        updateTotal();
    }

    private static void updateTotal(){
        float total = 0;
        for (Item i: itemList) {
            total += (i.amount * i.price);
        }
        tv_total.setText("Total: " + new BigDecimal(Float.toString(total)).setScale(2, RoundingMode.HALF_UP) + "$");
    }

    public static  void updateFavourite(int position){
        itemList.get(position).isFavourite = !itemList.get(position).isFavourite;

        Collections.sort(itemList, (item1, item2) -> Boolean.compare(!item1.isFavourite, !item2.isFavourite));

        adapter.notifyDataSetChanged();
    }
}