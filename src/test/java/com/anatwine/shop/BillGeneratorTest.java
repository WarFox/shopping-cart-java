package com.anatwine.shop;

import org.junit.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class BillGeneratorTest {

    @Test
    public void testGenerateBillWithOutOffers() throws Exception {
        ShoppingCart shoppingCart = new ShoppingCart(new ArrayList<>());
        BillGenerator billGenerator = new BillGenerator();
        Bill bill = billGenerator.generateBill(shoppingCart);
        assertThat(bill.getSubtotal()).isZero();
    }

}
