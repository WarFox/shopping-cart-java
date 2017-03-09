package com.anatwine.shop.models;

import java.util.Map;

/**
 * Price list is a map of String -> Double
 *
 */
public class PriceList {

    private final Map<String, Double> priceList;

    public PriceList(Map<String, Double> priceList) {
        this.priceList = priceList;
    }

    public Double getPrice(String name) {
        Double price = priceList.get(name);
        if (price == null) {
            // this means an item that is not in the inventory
            // was purchased
            // TODO we should probably log this as error or abort
            // For new log the event and return 0
           System.out.println(name + " not in inventory");
        }
        return price == null ? 0.0 : price;
    }

}
