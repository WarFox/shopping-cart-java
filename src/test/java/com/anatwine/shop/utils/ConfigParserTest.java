package com.anatwine.shop.utils;

import com.anatwine.shop.models.DiscountItem;
import com.anatwine.shop.models.OfferItem;
import com.anatwine.shop.models.PriceList;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import com.typesafe.config.ConfigValue;
import com.typesafe.config.ConfigValueFactory;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.AbstractMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class ConfigParserTest {

    @Test
    public void testParseDiscountItem() throws Exception {
        Config config = Mockito.mock(Config.class);
        when(config.getString("name")).thenReturn("Apple");
        when(config.getDouble("discountPercent")).thenReturn(20.0);

        DiscountItem discountItem = ConfigParser.parseDiscountItem(config);
        assertThat(discountItem.getName()).isEqualTo("Apple");
        assertThat(discountItem.getDiscountPercent()).isEqualTo(20);
    }

    @Test
    public void testParseOfferItem() throws Exception {
        Config discountConfig = Mockito.mock(Config.class);
        when(discountConfig.getString("name")).thenReturn("Apple");
        when(discountConfig.getDouble("discountPercent")).thenReturn(20.0);

        Config config = Mockito.mock(Config.class);
        when(config.getString("name")).thenReturn("Orange");
        when(config.getInt("quantity")).thenReturn(2);
        when(config.getConfig("discount")).thenReturn(discountConfig);

        OfferItem offerItem = ConfigParser.parseOfferItem(config);
        assertThat(offerItem.getName()).isEqualTo("Orange");
        assertThat(offerItem.getQuantity()).isEqualTo(2);
        DiscountItem discountItem = offerItem.getDiscountItem();
        assertThat(discountItem.getName()).isEqualTo("Apple");
        assertThat(discountItem.getDiscountPercent()).isEqualTo(20);
    }

    @Test
    public void testOffersFromConfig() throws Exception {
        Config config = ConfigFactory.load("test.conf");
        ConfigParser configParser = new ConfigParser(config);
        List<OfferItem> offers = configParser.getOffers();
        assertThat(offers).hasSize(3);
    }

    @Test
    public void testGetPriceListFromConfig() throws Exception {
        Config config = ConfigFactory.load("test.conf");
        ConfigParser configParser = new ConfigParser(config);
        PriceList priceList = configParser.getPriceList();
        assertThat(priceList.getPrice("Apple")).isEqualTo(1.00);
    }

    @Test
    public void testGetPriceListMockConfig() throws Exception {
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

        ConfigParser configParser = new ConfigParser(config);
        PriceList priceList = configParser.getPriceList();
        assertThat(priceList.getPrice("item1")).isEqualTo(50.0);
        assertThat(priceList.getPrice("item2")).isEqualTo(40.0);
        assertThat(priceList.getPrice("item3")).isEqualTo(30.0);
    }

}
