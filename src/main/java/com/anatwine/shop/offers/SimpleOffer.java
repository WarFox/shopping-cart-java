package com.anatwine.shop.offers;

import com.anatwine.shop.ShoppingCart;
import com.anatwine.shop.models.Discount;
import com.anatwine.shop.models.OfferItem;
import com.anatwine.shop.models.PriceList;
import com.anatwine.shop.utils.DiscountCalculator;

import java.util.List;

public class SimpleOffer implements Offer {

    private final PriceList priceList;
    private final OfferItem offerItem;
    private Offer next;

    public SimpleOffer(OfferItem offerItem, PriceList priceList) {
        this.offerItem = offerItem;
        this.priceList = priceList;
    }

    @Override
    public void applyOffer(ShoppingCart cart, List<Discount> discounts) {
        long itemCount = cart.getItemCount(offerItem.getName());

        if (itemCount >= offerItem.getQuantity()) {
            DiscountCalculator discountCalculator = new DiscountCalculator(cart, priceList);
            Discount discount = getDiscount(discountCalculator);
            discounts.add(discount);
        }

        if (hasNext()) {
            next.applyOffer(cart, discounts);
        }
    }

    @Override
    public void setNext(Offer next) {
        this.next = next;
    }

    public Discount getDiscount(DiscountCalculator discountCalculator) {
        return discountCalculator.calculateDiscount(offerItem);
    }

    public boolean hasNext() {
        return this.next != null;
    }

}
