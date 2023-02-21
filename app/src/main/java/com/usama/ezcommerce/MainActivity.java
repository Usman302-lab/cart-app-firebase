package com.usama.ezcommerce;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    ArrayList<ProductData> Data = new ArrayList<>();
    ProductAdapter adapter;

    private void filter(String text){
        ArrayList<ProductData> filteredData = new ArrayList<>();  ///// initialize empty list every time after search /////

        //// filter the data that is present in original arrayList  ////
        for(ProductData item: Data){
            if(item.getProductname().toLowerCase().contains(text.toLowerCase())){
                filteredData.add(item);
            }
        }

        adapter.filteredList(filteredData);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.Editsearch);

        //// Search filter ////
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());       /// this function will be called after every search
            }
        });


        /// Customer Service button ////
        Button support = (Button) findViewById(R.id.serviceButton);
        support.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = "46457666645";
                Uri u = Uri.parse("tel:" + number);

                // Create the intent and set the data for the
                // intent as the phone number.
                Intent i = new Intent(Intent.ACTION_DIAL, u);
                try {
                    // Launch the Phone app's dialer with a phone
                    // number to dial a call.
                    startActivity(i);
                } catch (SecurityException s){
                    // show() method display the toast with
                    // exception message.
                    Toast.makeText(MainActivity.this, "An error occurred", Toast.LENGTH_LONG).show();
                }

            }
        });


        Data.add(new ProductData(R.drawable.my_shirt, "Shirt", 3f, "$220"));
        Data.add(new ProductData(R.drawable.my_shoe, "Shoes", 2.3f, "$200"));
        Data.add(new ProductData(R.drawable.dumbbell, "Dumbbell", 4f, "$250"));
        Data.add(new ProductData(R.drawable.bag, "Bag", 3.5f, "$300"));
        Data.add(new ProductData(R.drawable.laptop, "Laptop", 3.5f, "$500"));
        Data.add(new ProductData(R.drawable.laptop, "Ipad", 2.5f, "$900"));

        //// save this arrayList in Database (send it to business layer [ProductData.java] ) //////
        ProductData dataobj = new ProductData();
        dataobj.saveToDB(Data);

        /// read products from firebase ////
        ArrayList<ProductData> products = new ArrayList<ProductData>();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference("/Product");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                /// get all children of root node (i.e; Product)
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                for(DataSnapshot child : children){
                    ProductData item1  = child.getValue(ProductData.class);
                    products.add(item1);
                }

                /// once all items read, display it ///
                populateProducts(products);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) { }
        });

    }


    public void populateProducts(ArrayList<ProductData> products){
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new ProductAdapter(MainActivity.this, products);
        recyclerView.addItemDecoration(new DividerItemDecoration(MainActivity.this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);
    }

}