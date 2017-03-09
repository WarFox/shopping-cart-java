package com.anatwine.shop.models;

/**
 * This model represents a discount that is
 */
public class Discount {

    private final String name;
    private final Double discountPercent;
    private final Double discountValue;

    public Discount(String name, Double discountPercent, Double discountValue) {
        this.name = name;
        this.discountPercent = discountPercent;
        this.discountValue = discountValue;
    }

    public String getName() {
        return name;
    }

    public Double getDiscountPercent() {
        return discountPercent;
    }

    public Double getDiscountValue() {
        return discountValue;
    }

}
