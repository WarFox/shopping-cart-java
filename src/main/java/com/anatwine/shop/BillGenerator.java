package com.anatwine.shop;

import com.anatwine.shop.models.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * This is the main class that generates a bill
 */
public class BillGenerator {

    private Map<String, Double> priceList;

    public BillGenerator(Map<String, Double> priceList) {
       this.priceList = priceList;
    }

    public Bill generateBill(ShoppingCart shoppingCart) {
        List<String> items = shoppingCart.getItems().stream().map(Product::getName).collect(Collectors.toList());
        Double subTotal = calculateSubTotal(items, priceList);
        return new Bill(subTotal, 0.0, new ArrayList<>());
    }

    public Double calculateSubTotal(List<String> items, Map<String, Double> priceList) {
        return items.stream().mapToDouble(priceList::get).sum();
    }

}
