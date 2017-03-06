package com.anatwine.shop;

import org.junit.Test;

import java.util.ArrayList;

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
    public void testGetMessagesEmpty() throws Exception {
        Bill bill = new Bill(10.0, 10.0, new ArrayList<>());
        assertThat(bill.getMessages()).isEmpty();
    }

}