package com.anatwine.shop;

import com.anatwine.shop.models.Bill;
import com.anatwine.shop.models.Discount;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;

public class AppTest {

    @Test
    public void testShoppingCart() throws Exception {
        ShoppingCart shoppingCart = App.getShoppingCart(new String[]{"apple", "apple", "orange"});
        assertThat(shoppingCart.getItemCount("apple")).isEqualTo(2);
        assertThat(shoppingCart.getItemCount("orange")).isEqualTo(1);
    }


    @Test
    public void testPrintBill() throws Exception {
        Bill bill = Mockito.spy(new Bill(10.10, 1.0, new ArrayList<>()));
        App.print(bill);
        Mockito.verify(bill, times(2)).getDiscounts();
        Mockito.verify(bill).getSubtotal();
        Mockito.verify(bill).getTotal();
    }

    @Test
    public void testPrintBillWithDiscounts() throws Exception {
        ArrayList<Discount> discounts = new ArrayList<>();
        discounts.add(new Discount("name", 10.10, 2.20));
        Bill bill = Mockito.spy(new Bill(10.10, 1.0, discounts));
        App.print(bill);
        Mockito.verify(bill, times(2)).getDiscounts();
        Mockito.verify(bill).getSubtotal();
        Mockito.verify(bill).getTotal();
    }

    @Test
    public void testPrintDiscount() throws Exception {
        Discount discount = Mockito.spy(new Discount("name", 10.0, 10.0));
        App.print(discount);
        Mockito.verify(discount).getName();
        Mockito.verify(discount).getDiscountPercent();
        Mockito.verify(discount).getDiscountValue();
    }

    @Test
    public void testGetBillGenerator() throws Exception {
        Config config = ConfigFactory.load("test.conf");
        BillGenerator billGenerator = App.getBillGenerator(config);
        ShoppingCart shoppingCart = new ShoppingCart(Arrays.asList("Apple", "Bread", "Bread", "Milk", "Soup", "Soup"));
        Bill bill = billGenerator.generateBill(shoppingCart);
        assertThat(bill.getSubtotal()).isEqualTo(5.2);
        assertThat(bill.getTotal()).isEqualTo(4.04);
        assertThat(bill.getDiscounts()).hasSize(3);
    }

}
