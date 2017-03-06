package com.anatwine.shop;

import com.anatwine.shop.models.Product;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    private List<Product> items = new ArrayList<>();

    public ShoppingCart(List<Product> items) {
        this.items = items;
    }

    public List<Product> getItems() {
        return items;
    }

}
