package com.anatwine.shop.models;

/**
 * This class represents a discount that is applied to a product
 * Discount is represented as percentage
 */
public class DiscountItem {

    private final String name;
    private final Double discountPercent;

    public DiscountItem(String name, Double discountPercent) {
        this.name = name;
        this.discountPercent = discountPercent;
    }

    public String getName() {
        return name;
    }

    public Double getDiscountPercent() {
        return discountPercent == null ? 0.0 : discountPercent;
    }

}
