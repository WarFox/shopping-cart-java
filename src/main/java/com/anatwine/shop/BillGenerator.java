package com.anatwine.shop;

import java.util.ArrayList;

/**
 * This is the main class that generates a bill
 */
public class BillGenerator {

    public Bill generateBill(ShoppingCart shoppingCart) {
        return new Bill(0.0, 0.0, new ArrayList<>());
    }

}
