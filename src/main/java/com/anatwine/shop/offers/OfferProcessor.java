package com.anatwine.shop.offers;

import com.anatwine.shop.ShoppingCart;
import com.anatwine.shop.models.Discount;
import com.anatwine.shop.models.OfferItem;
import com.anatwine.shop.models.PriceList;

import java.util.ArrayList;
import java.util.List;

/**
 * This class creates the offer chain
 */
public class OfferProcessor {

    private Offer prevHandler;
    private Offer rootHandler;

    public void addHandler(Offer handler) {
        if (prevHandler != null) {
            prevHandler.setNext(handler);
        } else {
            rootHandler = handler;
        }
        prevHandler = handler;
    }

    public List<Discount> applyOffer(ShoppingCart cart) {
        List<Discount> discounts = new ArrayList<>();
        if (rootHandler != null) {
            rootHandler.applyOffer(cart, discounts);
        }
        return discounts;
    }

    public static OfferProcessor createSimpleOfferProcessor(List<OfferItem> offerItems, PriceList priceList) {
        OfferProcessor offerProcessor = new OfferProcessor();
        for (OfferItem offerItem : offerItems) {
            offerProcessor.addHandler(new SimpleOffer(offerItem, priceList));
        }
        return offerProcessor;
    }

}
