package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class StoreAdapter extends ArrayAdapter<Item> {
    private ArrayList<Item> itemList;

    public StoreAdapter(@NonNull Context context, int resource, ArrayList<Item> itemList) {
        super(context, resource, itemList);
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView ==  null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.store_item, parent, false);
        }

        ImageView itemImage = convertView.findViewById(R.id.si_iv);
        TextView itemName = convertView.findViewById(R.id.si_tv_name);
        TextView itemPrice = convertView.findViewById(R.id.si_tv_price);
        ImageButton itemAdd = convertView.findViewById(R.id.si_btn_add);

        itemImage.setImageResource(itemList.get(position).imgResId);
        itemName.setText(itemList.get(position).name);
        itemPrice.setText(Float.toString(itemList.get(position).price) + "$");
        itemAdd.setImageResource(R.drawable.koszyk);

        itemAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GlobalData.getInstance().addItemToCart(position);
            }
        });

        return convertView;
    }
}
