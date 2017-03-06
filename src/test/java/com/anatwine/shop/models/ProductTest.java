package com.anatwine.shop.models;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductTest {

    @Test
    public void testGetNameGetsName() throws Exception {
        Product product = new Product("name", 0.90);
        assertThat(product.getName()).isEqualTo("name");
    }

    @Test
    public void testGetPrice() throws Exception {
        Product product = new Product("name", 0.90);
        assertThat(product.getPrice()).isEqualTo(0.90);
    }

}