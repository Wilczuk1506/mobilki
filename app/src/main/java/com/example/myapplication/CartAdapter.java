package com.example.myapplication;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CartAdapter extends ArrayAdapter<Item> {
    private ArrayList<Item> itemList;

    public CartAdapter(@NonNull Context context, int resource, ArrayList<Item> itemList) {
        super(context, resource, itemList);
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.cart_item, parent, false);
        }

        ImageView image = convertView.findViewById(R.id.ci_iv);
        TextView name = convertView.findViewById(R.id.ci_name);
        TextView price = convertView.findViewById(R.id.ci_price);
        ImageButton down = convertView.findViewById(R.id.ci_imgBtn_down);
        EditText amount = convertView.findViewById(R.id.ci_etn_amount);
        ImageButton up = convertView.findViewById(R.id.ci_imgBtn_up);
        ImageButton favourite = convertView.findViewById(R.id.ci_imgBtn_fav);
        ImageButton trash = convertView.findViewById(R.id.ci_imgBtn_buy);

        image.setImageResource(itemList.get(position).imgResId);
        name.setText(itemList.get(position).name);
        price.setText(Float.toString(itemList.get(position).price) + "$");
        down.setImageResource(R.drawable.down);
        amount.setText(Integer.toString(itemList.get(position).amount));
        up.setImageResource(R.drawable.up);
        favourite.setImageResource(GlobalData.getInstance().cartItems.get(position).isFavourite ? R.drawable.favouritetrue : R.drawable.favouritefalse);
        trash.setImageResource(R.drawable.trashcan);

        trash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity2.removeItem(position);
            }
        });

        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity2.modifyAmount(position, -1);
            }
        });

        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity2.modifyAmount(position, 1);
            }
        });

        amount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                MainActivity2.setAmount(position, Integer.parseInt(amount.getText().toString()));
            }
        });

        favourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity2.updateFavourite(position);
            }
        });

        return convertView;
    }
}
