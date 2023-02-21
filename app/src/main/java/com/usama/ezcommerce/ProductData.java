package com.usama.ezcommerce;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.Hashtable;

public class ProductData {
    private int imgId;
    private String price;
    private String productname;
    private float stars;

    public  ProductData(){
        imgId = 0;
        productname = "";
        stars = 0;

    }


    public ProductData(int imgId, String productname, float stars, String price){
        this.imgId = imgId;
        this.productname = productname;
        this.stars = stars;
        this.price = price;
    }


    //// Receive products from UI Layer & store them in Database //////
    public void saveToDB(ArrayList<ProductData> data){
        /// store it in database ////
        ProductDB temp = new ProductDB();
        temp.saveProducts(data);
    }


    public int getImgId() {
        return imgId;
    }

    public String getProductname() {
        return productname;
    }

    public float getStars() {
        return stars;
    }

    public String getPrice() {
        return price;
    }
}
