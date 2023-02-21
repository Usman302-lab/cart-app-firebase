package com.usama.ezcommerce;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;
import android.content.Context;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

public class ProductDB implements  ProductInterface{
    FirebaseDatabase database;
    DatabaseReference myRef;

    //// save a single Product /////
    @Override
    public void saveProduct(ProductData item){
        myRef.child(item.getProductname()).setValue(item);             /// inserting in firebase with unique product name ///
    }


    //// sava all Products /////
    @Override
    public void saveProducts(ArrayList<ProductData> products) {
        database = FirebaseDatabase.getInstance();			/// points to our database ///
        myRef = database.getReference("/Product");              /// points to particular table in database ///
        for(ProductData item : products){
            saveProduct(item);
        }
    }
}
