package com.anatwine.shop.offers;

import com.anatwine.shop.ShoppingCart;
import com.anatwine.shop.models.Discount;

import java.util.List;

interface Offer {

    void setNext(Offer offer);

    /**
     * Apply offer in to Shopping cart based on the price list
     * and add discounts to discounts object
     *
     * @param cart      - Shopping cart object to apply offer in the chain
     * @param discounts - This list should be populated by each handler in the chain
     *                  An offer can be added or removed to this List of discounts,
     *                  along the chain
     */
    void applyOffer(ShoppingCart cart, List<Discount> discounts);

}
