package com.usama.ezcommerce;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class Page3 extends AppCompatActivity {
    public static  TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page3);

        textView = (TextView) findViewById(R.id.textView10);
        Button pay = (Button) findViewById(R.id.button5);
        Button cancel = (Button) findViewById(R.id.button6);


        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Page3.this, "Payment Done", Toast.LENGTH_LONG).show();
            }
        });


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Page3.this, "Payment Cancel", Toast.LENGTH_LONG).show();
            }
        });


        Intent intent = getIntent();
        String item = intent.getStringExtra("UniqueItem1");
        String price = intent.getStringExtra("UniquePrice1");
        int imgID = intent.getIntExtra("UniqueImage1", 0);


        //// a check in cart (if item already in cart, then only increase in its quantity) ////
        boolean exists = findItemInCart(item);
        if(!exists) {
            MainActivity.cart.add(new CartData(imgID, item, price, 1));
        }
        else{
            int Itemindex = findItemIndex(item);
            if (Itemindex != -1)
                MainActivity.cart.get(Itemindex).incrementQuantity();

        }


        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView2);
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        CartAdapter adapter = new CartAdapter(Page3.this, MainActivity.cart);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);

        textView.setText(String.valueOf(adapter.getTotal()));              //// set total price ////
    }

    public boolean findItemInCart(String item){
        for(CartData cart : MainActivity.cart) {
            if (cart.getProductname().equals(item))
                return true;
        }
        return false;
    }

    public int findItemIndex(String item){
        int i = 0;
        boolean flag = false;
        for(CartData cart : MainActivity.cart) {
            if (cart.getProductname().equals(item))
                return i;
            else
                i++;
        }
        return -1;
    }
}
