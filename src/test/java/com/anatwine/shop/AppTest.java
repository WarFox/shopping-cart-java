package com.anatwine.shop;

import com.anatwine.shop.models.PriceList;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigValue;
import com.typesafe.config.ConfigValueFactory;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.mockito.Mockito;

import java.util.AbstractMap;
import java.util.HashSet;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest extends TestCase {

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

}
