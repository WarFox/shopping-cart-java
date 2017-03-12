package com.anatwine.shop.offers;

import com.anatwine.shop.ShoppingCart;
import com.anatwine.shop.models.Discount;
import com.anatwine.shop.models.DiscountItem;
import com.anatwine.shop.models.OfferItem;
import com.anatwine.shop.models.PriceList;
import com.anatwine.shop.utils.DiscountCalculator;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;

public class SimpleOfferTest {

    @Test
    public void testHasNextFalse() throws Exception {
        OfferItem offerItem = Mockito.mock(OfferItem.class);
        PriceList priceList = Mockito.mock(PriceList.class);
        SimpleOffer simpleOffer = new SimpleOffer(offerItem, priceList);
        assertThat(simpleOffer.hasNext()).isFalse();
    }

    @Test
    public void testHasNextReturnsTrueAfterSettingNext() throws Exception {
        OfferItem offerItem = Mockito.mock(OfferItem.class);
        PriceList priceList = Mockito.mock(PriceList.class);
        SimpleOffer simpleOffer = new SimpleOffer(offerItem, priceList);
        simpleOffer.setNext(Mockito.mock(SimpleOffer.class));
        assertThat(simpleOffer.hasNext()).isTrue();
    }

    @Test
    public void testGetDiscount() throws Exception {
        OfferItem offerItem = Mockito.mock(OfferItem.class);
        PriceList priceList = Mockito.mock(PriceList.class);
        SimpleOffer simpleOffer = new SimpleOffer(offerItem, priceList);
        DiscountCalculator discountCalculator = Mockito.mock(DiscountCalculator.class);
        Mockito.when(discountCalculator.calculateDiscount(any()))
                .thenReturn(new Discount("item", 10.0, 1.0));
        Discount discount = simpleOffer.getDiscount(discountCalculator);
        assertThat(discount.getDiscountPercent()).isEqualTo(10.0);
        assertThat(discount.getDiscountValue()).isEqualTo(1.0);
        assertThat(discount.getName()).isEqualTo("item");
    }


    @Test
    public void applyOfferWithNothingInChain() throws Exception {
        OfferItem offerItem = new OfferItem("Bread", 2, new DiscountItem("Apple", 10.0));
        PriceList priceList = Mockito.mock(PriceList.class);
        SimpleOffer simpleOffer = new SimpleOffer(offerItem, priceList);
        ShoppingCart shoppingCart = Mockito.mock(ShoppingCart.class);
        Mockito.when(shoppingCart.getItemCount(any()))
                .thenReturn(2L);
        ArrayList<Discount> discounts = new ArrayList<>();
        simpleOffer.applyOffer(shoppingCart, discounts);
        assertThat(discounts).hasSize(1);
        assertThat(discounts.get(0).getDiscountValue()).isEqualTo(0.0);
    }

    @Test
    public void applyOfferWithTwoOffersInChain() throws Exception {
        OfferItem offerItem = new OfferItem("Bread", 2, new DiscountItem("Apple", 10.0));
        PriceList priceList = Mockito.mock(PriceList.class);
        SimpleOffer simpleOffer = new SimpleOffer(offerItem, priceList);
        simpleOffer.setNext(Mockito.mock(SimpleOffer.class));
        ShoppingCart shoppingCart = Mockito.mock(ShoppingCart.class);
        Mockito.when(shoppingCart.getItemCount(any()))
                .thenReturn(2L);
        ArrayList<Discount> discounts = new ArrayList<>();
        simpleOffer.applyOffer(shoppingCart, discounts);
        assertThat(discounts).hasSize(1);
        assertThat(discounts.get(0).getDiscountValue()).isEqualTo(0.0);
    }

    @Test
    public void applyOfferNotEnoughItemsPurchased() throws Exception {
        OfferItem offerItem = new OfferItem("Bread", 2, new DiscountItem("Apple", 10.0));
        PriceList priceList = Mockito.mock(PriceList.class);
        SimpleOffer simpleOffer = new SimpleOffer(offerItem, priceList);
        simpleOffer.setNext(Mockito.mock(SimpleOffer.class));
        ShoppingCart shoppingCart = Mockito.mock(ShoppingCart.class);
        Mockito.when(shoppingCart.getItemCount(any()))
                .thenReturn(1L);
        ArrayList<Discount> discounts = new ArrayList<>();
        simpleOffer.applyOffer(shoppingCart, discounts);
        assertThat(discounts).isEmpty();
    }

}
