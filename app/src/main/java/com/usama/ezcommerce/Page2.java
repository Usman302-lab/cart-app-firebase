package com.usama.ezcommerce;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Page2 extends AppCompatActivity {
    String item, price;
    int imgID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page2);

        String description = "Online Shopping is defined as the act of buying products or services over the internet. It is a form of electronic commerce which allows consumers to directly buy goods or" +
                " services from a seller without an intermediary service. A large variety of goods are available online, ranging from " +
                "books and clothes to electronics and furniture. Online shopping has become increasingly popular due to the convenience and ease it offers. " +
                "There are several benefits of online shopping. Firstly, it is very convenient as you can shop from the comfort of your own home. Secondly, it is very fast" +
                " and you can find what you are looking for quickly and easily. Thirdly, it is very cheap as there are no middlemen involved. Fourthly, there is a wide range of products available online.";

        Intent intent = getIntent();
        item = intent.getStringExtra("UniqueItem");
        price = intent.getStringExtra("UniquePrice");
        imgID = intent.getIntExtra("UniqueImage", 0);
        float stars = intent.getFloatExtra("UniqueStars", 0);
        String des = "Description";

        ImageView imageview = (ImageView) findViewById(R.id.imageView3);
        TextView textView1 = (TextView) findViewById(R.id.textView1);
        TextView textView2 = (TextView) findViewById(R.id.textView2);
        TextView textView3 = (TextView) findViewById(R.id.textView3);
        TextView textView4 = (TextView) findViewById(R.id.textView4);
        RatingBar ratingbar = (RatingBar) findViewById(R.id.ratingBar2);
        Button button = (Button) findViewById(R.id.button);

        imageview.setImageResource(imgID);
        textView1.setText(item);
        textView2.setText(price);
        ratingbar.setRating(stars);
        textView3.setText(des);
        textView4.setText(description);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(Page2.this, Page3.class);
                intent1.putExtra("UniqueImage1", imgID);
                intent1.putExtra("UniqueItem1", item);
                intent1.putExtra("UniquePrice1", price);
                Page2.this.startActivity(intent1);
            }
        });
    }

}