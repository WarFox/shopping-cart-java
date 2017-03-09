package com.anatwine.shop.models;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DiscountTest {

    @Test
    public void testGetName() throws Exception {
        Discount discount = new Discount("name", 10.0, 1.0);
        assertThat(discount.getName()).isEqualTo("name");
    }

    @Test
    public void testGetDiscountPercent() throws Exception {
        Discount discount = new Discount("name", 10.0, 1.0);
        assertThat(discount.getDiscountPercent()).isEqualTo(10.0);
    }

    @Test
    public void testGetDiscountValue() throws Exception {
        Discount discount = new Discount("name", 10.0, 1.0);
        assertThat(discount.getDiscountValue()).isEqualTo(1.0);
    }


}
