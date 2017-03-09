package com.anatwine.shop.models;

/**
 * This class represents an an Item that is on offer
 * The offer is based on quantity of this item purchased
 * and discount is applied to the DiscountItem
 */
public class OfferItem {

    private String name;
    private int quantity;
    private DiscountItem discountItem;

    public OfferItem(String name, int quantity, DiscountItem discountItem) {
        this.name = name;
        this.quantity = quantity;
        this.discountItem = discountItem;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public DiscountItem getDiscountItem() {
        return discountItem;
    }

}
