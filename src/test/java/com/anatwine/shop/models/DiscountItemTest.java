package com.anatwine.shop.models;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DiscountItemTest {

    @Test
    public void testGetName() throws Exception {
        DiscountItem discountItem = new DiscountItem("name", null);
        assertThat(discountItem.getName()).isEqualTo("name");
    }

    @Test
    public void testGetDiscountPercent() throws Exception {
        DiscountItem discountItem = new DiscountItem("name", 10.0);
        assertThat(discountItem.getDiscountPercent()).isEqualTo(10.0);
    }

    @Test
    public void testGetDiscountPercentShouldBeZeroWhenNull() throws Exception {
        DiscountItem discountItem = new DiscountItem("name", null);
        assertThat(discountItem.getDiscountPercent()).isEqualTo(0.0);
    }

}
