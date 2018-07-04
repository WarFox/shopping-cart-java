package com.anatwine.shop.utils;

import com.anatwine.shop.ShoppingCart;
import com.anatwine.shop.models.DiscountItem;
import com.anatwine.shop.models.OfferItem;
import com.anatwine.shop.models.PriceList;
import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class DiscountCalculatorTest {

    private static final Map<String, Double> priceList = new HashMap<>();

    @BeforeClass
    public static void setUp() throws Exception {
        priceList.put("Orange", 1.30);
        priceList.put("Apple", 1.0);
        priceList.put("Bread", 0.80);
        priceList.put("Soup", .65);
    }

    @Test
    public void test50PercentDiscountOn1ItemAt40PerItem() throws Exception {
        Double discount = DiscountCalculator.calculateDiscount(1, 40.0, 50.0);
        assertThat(discount).isEqualTo(20.0);
    }

    @Test
    public void test50PercentDiscountOn2ItemAt40PerItem() throws Exception {
        Double discount = DiscountCalculator.calculateDiscount(2, 40.0, 50.0);
        assertThat(discount).isEqualTo(40.0);
    }

    @Test
    public void test50PercentDiscountOn3ItemAt30PerItem() throws Exception {
        Double discount = DiscountCalculator.calculateDiscount(3, 30.0, 50.0);
        assertThat(discount).isEqualTo(45.0);
    }

    @Test
    public void test25PercentDiscountOn3ItemAt30PerItem() throws Exception {
        Double discount = DiscountCalculator.calculateDiscount(7, 37.0, 9.33);
        assertThat(discount).isEqualTo(22.5);
    }

    @Test
    public void test10PercentDiscountOn3ItemAt1PerItem() throws Exception {
        Double discount = DiscountCalculator.calculateDiscount(3, 1.0, 10.0);
        assertThat(discount).isEqualTo(0.30);
    }

    @Test
    public void testZeroPercentDiscountOnItems() throws Exception {
        Double discount = DiscountCalculator.calculateDiscount(5, 10.0, 0.0);
        assertThat(discount).isEqualTo(0.0);
    }

    @Test
    public void testCalculateDiscountForAnItem() throws Exception {
        List<String> items = Arrays.asList("Bread", "Bread", "Apple");
        OfferItem offerItem = new OfferItem("Bread", 2, new DiscountItem("Apple", 10.0));
        ShoppingCart shoppingCart = new ShoppingCart(items);
        DiscountCalculator discountCalculator = new DiscountCalculator(shoppingCart, new PriceList(priceList));
        discountCalculator.calculateDiscount(offerItem);
        Double discount = DiscountCalculator.calculateDiscount(5, 10.0, 10.0);
        assertThat(discount).isEqualTo(5);
    }

    @Test
    public void testCalculateDiscountForAnItemZeroPercent() throws Exception {
        List<String> items = Arrays.asList("Bread", "Bread", "Apple");
        OfferItem offerItem = new OfferItem("Bread", 2, new DiscountItem("Apple", 10.0));
        ShoppingCart shoppingCart = new ShoppingCart(items);
        DiscountCalculator discountCalculator = new DiscountCalculator(shoppingCart, new PriceList(priceList));
        discountCalculator.calculateDiscount(offerItem);
        Double discount = DiscountCalculator.calculateDiscount(5, 10.0, 0.0);
        assertThat(discount).isEqualTo(0.0);
    }


    @Test
    public void testCalculateDiscountBigDecimalZero() throws Exception {
        BigDecimal bigDecimal = DiscountCalculator.calculateDiscount(0l, BigDecimal.valueOf(20), BigDecimal.ONE);
        assertThat(bigDecimal).isZero();
    }

    @Test
    public void testCalculateDiscountBigDecimal() throws Exception {
        BigDecimal price = BigDecimal.valueOf(20);
        BigDecimal discountPercent = BigDecimal.ONE;
        Long count = 2l;

        BigDecimal ten = BigDecimal.TEN;
        BigDecimal divident = BigDecimal.valueOf(36.0);
        assertThat(divident.divide(ten)).isEqualTo(BigDecimal.valueOf(36));

        BigDecimal discount1 = DiscountCalculator.calculateDiscount(3l, BigDecimal.valueOf(30.0), BigDecimal.valueOf(50.0));
        assertThat(discount1).isEqualTo(BigDecimal.valueOf(45.00));

        BigDecimal discount = DiscountCalculator.calculateDiscount(7l, BigDecimal.valueOf(37.0), BigDecimal.valueOf(9.33));
        BigDecimal bigDecimal =  DiscountCalculator.calculateDiscount(count, price, discountPercent);
        assertThat(bigDecimal).isEqualTo(BigDecimal.valueOf(0.04));
        assertThat(discount).isEqualTo(BigDecimal.ONE);
    }

}
