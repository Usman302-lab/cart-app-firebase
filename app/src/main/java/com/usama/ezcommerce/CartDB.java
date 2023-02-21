package com.usama.ezcommerce;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CartDB implements CartInterface{
    FirebaseDatabase database;
    DatabaseReference myRef;

    public CartDB(){
        database = FirebaseDatabase.getInstance();			/// points to our database ///
        myRef = database.getReference("/Cart");              /// points to particular table in database ///
    }


    public void saveCartItem(CartData item){
        String ItemName = item.getProductname();
        Query checkItem = myRef.orderByChild("productname").equalTo(ItemName);

        /// checkItem will have record (item) that matching to our query ///

        checkItem.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists()){            /// it means item is already in cart, only increase quantity ///
                    int quantity = snapshot.child(ItemName).child("/quantity").getValue(int.class);
                    quantity++;
                    setCartItemQuantity(item, quantity);
                }
                else{                                    /// new item, add it in cart ///
                    myRef.child(ItemName).setValue(item);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });

    }

    public void setCartItemQuantity(CartData cartItem, int quantity){
        String ItemName = cartItem.getProductname();
        myRef.child(ItemName).child("/quantity").setValue(quantity);
    }


    public void deleteCartItem(String itemName){
        myRef.child(itemName).removeValue();
    }

    public void ShowPrice(){
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int price = 0;
                Iterable<DataSnapshot> children = snapshot.getChildren();
                for(DataSnapshot child : children) {
                    CartData item = child.getValue(CartData.class);
                    int Itemprice = convertString(item.getPrice());
                    price = price + (item.getQuantity() * Itemprice);
                }
                Page3.textView.setText(String.valueOf(price));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });

    }

    public int convertString(String str) {
        char[] ch = str.toCharArray();
        int len = ch.length;

        char[] arr = new char[len-1];
        for(int i=0; i<(len-1); i++){
           arr[i] = ch[i+1];
        }

        int price = Integer.parseInt(String.valueOf(arr));
        return price;
    }
}
