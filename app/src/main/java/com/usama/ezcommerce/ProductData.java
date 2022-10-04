package com.usama.ezcommerce;

public class ProductData {
    private int imgId;
    private String productname;
    private float stars;
    private String price;

    public ProductData(int imgId, String productname, float stars, String price){
        this.imgId = imgId;
        this.productname = productname;
        this.stars = stars;
        this.price = price;
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
