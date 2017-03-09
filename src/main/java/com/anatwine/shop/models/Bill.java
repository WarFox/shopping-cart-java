package com.anatwine.shop.models;

import java.util.List;

/**
 * The Bill has subtotal, total and list of discounts
 * Message can be generated based on the discounts available
 */
public class Bill {

    private final Double subtotal;
    private final Double total;
    private final List<Discount> discounts;

    public Bill(Double subtotal, Double total, List<Discount> discounts) {
        this.subtotal = subtotal;
        this.total = total;
        this.discounts = discounts;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public Double getTotal() {
        return total;
    }

    public List<Discount> getDiscounts() {
        return discounts;
    }

}
