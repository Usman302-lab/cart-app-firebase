package com.usama.ezcommerce;

public class CartData {
    private int imgId;
    private String productname;
    private String price;
    private int quantity;

    public CartData(int imgId, String productname, String price, int quantity){
        this.imgId = imgId;
        this.productname = productname;
        this.price = price;
        this.quantity = quantity;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    public void incrementQuantity(){
        quantity++;
    }

    public void decrementnQuantity(){
        quantity--;
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
