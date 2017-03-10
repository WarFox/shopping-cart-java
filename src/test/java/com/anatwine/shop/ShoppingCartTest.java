package com.anatwine.shop;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ShoppingCartTest {

    @Test
    public void testGetItemsReturnsItemsInBasketEmpty() throws Exception {
        List<String> items = new ArrayList<>();
        assertThat(new ShoppingCart(items).getItemCountMap()).isEmpty();
    }

    @Test
    public void testGetItemsReturnsItemsInBasket() throws Exception {
        List<String> items = Arrays.asList("Apple", "Milk", "Bread");
        ShoppingCart shoppingCart = new ShoppingCart(items);
        assertThat(shoppingCart.getItemCountMap()).isNotEmpty();
        assertThat(shoppingCart.getItemCountMap()).hasSize(3);
    }

    @Test
    public void testGetItems() throws Exception {
        List<String> items = Arrays.asList("Apple", "Milk", "Bread");
        ShoppingCart shoppingCart = new ShoppingCart(items);
        assertThat(shoppingCart.getItems()).isEqualTo(items);
    }

    @Test
    public void testGetItem() throws Exception {
        List<String> items = Arrays.asList("Apple", "Milk", "Bread");
        ShoppingCart shoppingCart = new ShoppingCart(items);
        assertThat(shoppingCart.getItemCount("Apple")).isEqualTo(1L);
    }

    @Test
    public void testGetItemThatDoesNotExist() throws Exception {
        List<String> items = Arrays.asList("Apple", "Milk", "Bread");
        ShoppingCart shoppingCart = new ShoppingCart(items);
        assertThat(shoppingCart.getItemCount("DoesNotExist")).isZero();
    }

}
