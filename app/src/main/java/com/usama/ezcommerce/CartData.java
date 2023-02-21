package com.usama.ezcommerce;

import android.util.Log;

import java.util.ArrayList;
import java.util.Hashtable;

public class CartData {
    private int imgId;
    private String productname;
    private String price;
    private int quantity;

    public  CartData(){
        imgId = 0;
        productname = "";
        price = "";
        quantity = 0;
    }


    public CartData(int imgId, String productname, String price, int quantity){
        this.imgId = imgId;
        this.productname = productname;
        this.price = price;
        this.quantity = quantity;
    }


    //// Receive Cart Items from UI Layer & store them in Database //////
    public void saveCartItemToDB(CartData data){
        CartDB Interface = new CartDB();
        Interface.saveCartItem(data);
    }

    public void GetPrice(){
        CartDB Interface = new CartDB();
        Interface.ShowPrice();
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    public int getQuantity(){
        return quantity;
    }

    public int getImgId() {
        return imgId;
    }

    public String getProductname() {
        return productname;
    }

    public String getPrice() {
        return price;
    }
}
