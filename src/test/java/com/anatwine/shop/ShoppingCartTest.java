package com.anatwine.shop;

import com.anatwine.shop.models.Product;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class ShoppingCartTest {

    @Test
    public void testGetItemsReturnsItemsInBasketEmpty() throws Exception {
        List<Product> items = new ArrayList<>();
        assertThat(new ShoppingCart(items).getItems()).isEmpty();
    }

    @Test
    public void testGetItemsReturnsItemsInBaske() throws Exception {
        List<Product> items = new ArrayList<>();
        items.add(new Product("Apple", 0.90));
        items.add(new Product("Milk", 1.30));
        items.add(new Product("Bread", 0.90));
        ShoppingCart shoppingCart = new ShoppingCart(items);
        assertThat(shoppingCart.getItems()).isNotEmpty();
        assertThat(shoppingCart.getItems()).hasSize(3);
    }

}
