package com.anatwine.shop.utils;

import com.anatwine.shop.ShoppingCart;
import com.anatwine.shop.models.Discount;
import com.anatwine.shop.models.DiscountItem;
import com.anatwine.shop.models.OfferItem;
import com.anatwine.shop.models.PriceList;

/**
 * Logic to calculate discount
 */
public class DiscountCalculator {

    private PriceList priceList;
    private ShoppingCart cart;

    public DiscountCalculator(ShoppingCart cart, PriceList priceList) {
        this.priceList = priceList;
        this.cart = cart;
    }

    public Discount calculateDiscount(OfferItem offerItem) {
        DiscountItem discountItem = offerItem.getDiscountItem();
        Double discountPercent = discountItem.getDiscountPercent();
        String discountItemName = discountItem.getName();
        Long discountItemCount = cart.getItemCount(discountItemName);
        Double discountItemPrice = priceList.getPrice(discountItemName);
        Double discountValue = calculateDiscount(discountItemCount, discountItemPrice, discountPercent);
        return new Discount(discountItemName, discountPercent, discountValue);
    }

    public static Double calculateDiscount(long count, Double price, Double discountPercent) {
        return (count * price * discountPercent) / 100;
    }

}
