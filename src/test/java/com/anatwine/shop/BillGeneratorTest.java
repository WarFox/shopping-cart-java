package com.anatwine.shop;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class BillGeneratorTest {

    private static final Map<String, Double> priceList = new HashMap<>();

    @BeforeClass
    public static void setUp() throws Exception {
        priceList.put("Apple", 1.30);
        priceList.put("Orange", 1.30);
    }

    @Test
    public void testGenerateBillWithOutOffers() throws Exception {
        ShoppingCart shoppingCart = new ShoppingCart(new ArrayList<>());
        BillGenerator billGenerator = new BillGenerator(priceList);
        Bill bill = billGenerator.generateBill(shoppingCart);
        assertThat(bill.getSubtotal()).isZero();
    }

    @Test
    public void testCalculateSubtotal() throws Exception {
        BillGenerator billGenerator = new BillGenerator(priceList);
        List<String> items = Arrays.asList("Apple", "Orange", "Apple", "Orange");
        Double subTotal = billGenerator.calculateSubTotal(items, priceList);
        assertThat(subTotal).isEqualTo(5.20);
    }

}
