package com.usama.ezcommerce;

import java.util.ArrayList;
import java.util.Hashtable;

public interface CartInterface {
    public void saveCartItem(CartData item);
    public void setCartItemQuantity(CartData cartItem, int quantity);
    public void deleteCartItem(String name);
    public void ShowPrice();
    public int convertString(String strprice);
}
