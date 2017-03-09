package com.anatwine.shop.models;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class OfferItemTest {

    @Test
    public void testGetName() throws Exception {
        DiscountItem discountItem = new DiscountItem("discountItemName", 1.0);
        OfferItem offerItem = new OfferItem("name", 1, discountItem);
        assertThat(offerItem.getName()).isEqualTo("name");
    }

    @Test
    public void testGetQuantity() throws Exception {
        DiscountItem discountItem = new DiscountItem("discountItemName", 1.0);
        OfferItem offerItem = new OfferItem("name", 1, discountItem);
        assertThat(offerItem.getQuantity()).isEqualTo(1);
    }

    @Test
    public void testGetDiscountItem() throws Exception {
        DiscountItem discountItem = new DiscountItem("discountItemName", 1.0);
        OfferItem offerItem = new OfferItem("name", 0, discountItem);
        assertThat(offerItem.getDiscountItem().getName()).isEqualTo("discountItemName");
        assertThat(offerItem.getDiscountItem().getDiscountPercent()).isEqualTo(1.0);
    }

}
