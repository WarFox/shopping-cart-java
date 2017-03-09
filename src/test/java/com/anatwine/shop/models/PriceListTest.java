package com.anatwine.shop.models;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class PriceListTest {

    private static final Map<String, Double> priceList = new HashMap<>();

    @BeforeClass
    public static void setUp() throws Exception {
        priceList.put("Orange", 1.30);
        priceList.put("Apple", 1.0);
        priceList.put("Bread", 0.80);
        priceList.put("Soup", .65);
        priceList.put("Milk", 1.30);
    }

    @Test
    public void testGetPriceShouldReturnPrice() throws Exception {
        PriceList priceList = new PriceList(PriceListTest.priceList);
        assertThat(priceList.getPrice("Orange")).isEqualTo(1.30);
        assertThat(priceList.getPrice("Apple")).isEqualTo(1.0);
        assertThat(priceList.getPrice("Bread")).isEqualTo(0.80);
        assertThat(priceList.getPrice("Soup")).isEqualTo(0.65);
        assertThat(priceList.getPrice("Milk")).isEqualTo(1.30);
    }

    @Test
    public void testIfItemNotFoundReturnZero() throws Exception {
        PriceList priceList = new PriceList(PriceListTest.priceList);
        assertThat(priceList.getPrice("DoesNotExist")).isEqualTo(0.0);
    }

}
