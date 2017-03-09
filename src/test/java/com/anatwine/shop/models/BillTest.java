package com.anatwine.shop.models;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BillTest {

    @Test
    public void testGetSubtotal() throws Exception {
        Bill bill = new Bill(10.0, 10.0, new ArrayList<>());
        assertThat(bill.getSubtotal()).isEqualTo(10.0);
    }

    @Test
    public void testGetTotal() throws Exception {
        Bill bill = new Bill(10.0, 10.0, new ArrayList<>());
        assertThat(bill.getTotal()).isEqualTo(10.0);
    }

    @Test
    public void testEmptyDiscounts() throws Exception {
        Bill bill = new Bill(10.0, 10.0, new ArrayList<>());
        assertThat(bill.getDiscounts()).isEmpty();
    }

    @Test
    public void testGetDiscounts() throws Exception {
        List<Discount> discounts = new ArrayList<>();
        discounts.add(new Discount("item1", 10.0, 1.0));
        discounts.add(new Discount("item2", 10.0, 1.0));
        discounts.add(new Discount("item3", 10.0, 1.0));
        Bill bill = new Bill(10.0, 10.0, discounts);
        assertThat(bill.getDiscounts()).isNotEmpty();
        assertThat(bill.getDiscounts()).hasSize(3);
    }

}
