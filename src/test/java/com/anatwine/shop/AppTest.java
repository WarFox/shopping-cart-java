package com.anatwine.shop;

import com.anatwine.shop.models.Bill;
import com.anatwine.shop.models.Discount;
import com.anatwine.shop.models.OfferItem;
import com.anatwine.shop.models.PriceList;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigValue;
import com.typesafe.config.ConfigValueFactory;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;

public class AppTest {

    @Test
    public void testCreatePriceList() throws Exception {
        HashSet<Map.Entry<String, ConfigValue>> entries = new HashSet<>();
        entries.add(new AbstractMap.SimpleEntry<>("item1", ConfigValueFactory.fromAnyRef(50.0)));
        entries.add(new AbstractMap.SimpleEntry<>("item2", ConfigValueFactory.fromAnyRef(40.0)));
        entries.add(new AbstractMap.SimpleEntry<>("item3", ConfigValueFactory.fromAnyRef(30.0)));

        Config priceConfig = Mockito.mock(Config.class);
        Mockito.when(priceConfig.entrySet()).thenReturn(entries);
        Mockito.when(priceConfig.getDouble("item1")).thenReturn(50.0);
        Mockito.when(priceConfig.getDouble("item2")).thenReturn(40.0);
        Mockito.when(priceConfig.getDouble("item3")).thenReturn(30.0);

        Config config = Mockito.mock(Config.class);
        Mockito.when(config.getConfig("price")).thenReturn(priceConfig);

        PriceList priceList = App.createPriceList(config);
        assertThat(priceList.getPrice("item1")).isEqualTo(50.0);
        assertThat(priceList.getPrice("item2")).isEqualTo(40.0);
        assertThat(priceList.getPrice("item3")).isEqualTo(30.0);
    }

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
    public void testPrintDiscount() throws Exception {
        Discount discount = Mockito.spy(new Discount("name", 10.0, 10.0));
        App.print(discount);
        Mockito.verify(discount).getName();
        Mockito.verify(discount).getDiscountPercent();
        Mockito.verify(discount).getDiscountValue();
    }

    @Test
    public void testGetOffers() throws Exception {
        List<OfferItem> offers = App.getOffers();
        assertThat(offers).hasSize(2);
    }

}
