package com.anatwine.shop.offers;

import com.anatwine.shop.ShoppingCart;
import com.anatwine.shop.models.Discount;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class OfferProcessorTest {

    static class TestOffer implements Offer {
        Offer next;

        @Override
        public void setNext(Offer offer) {
            this.next = offer;
        }

        @Override
        public void applyOffer(ShoppingCart cart, List<Discount> discounts) {
            discounts.add(new Discount("name", 10.0, 1.0));
            if(next != null) {
                next.applyOffer(cart, discounts);
            }
        }
    }

    @Test
    public void testAddHandlerAndApplyOffer() throws Exception {
        OfferProcessor offerProcessor = new OfferProcessor();
        offerProcessor.addHandler(new TestOffer());
        offerProcessor.addHandler(new TestOffer());
        offerProcessor.addHandler(new TestOffer());
        List<Discount> discounts = offerProcessor.applyOffer(Mockito.mock(ShoppingCart.class));
        assertThat(discounts).hasSize(3);
    }

}
