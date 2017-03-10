package com.anatwine.shop.utils;

import org.junit.Test;

import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;

public class CurrencyFormatTest {

    @Test
    public void testCurrencyWithFractionalUnit() throws Exception {
        CurrencyFormat currencyFormat = new CurrencyFormat(Locale.UK);
        String format = currencyFormat.format(10.10);
        assertThat(format).isEqualTo("£10.10");
    }

    @Test
    public void testCurrencyFormatWithOnlyFractionalUnit() throws Exception {
        CurrencyFormat currencyFormat = new CurrencyFormat(Locale.UK);
        String format = currencyFormat.format(0.10);
        assertThat(format).isEqualTo("10p");
    }

    @Test
    public void testFractionalUnitUK() throws Exception {
        CurrencyFormat currencyFormat = new CurrencyFormat(Locale.UK);
        assertThat(currencyFormat.fractionalUnit()).isEqualTo("p");
    }

    @Test
    public void testFractionalUnitUSD() throws Exception {
        CurrencyFormat currencyFormat = new CurrencyFormat(Locale.US);
        assertThat(currencyFormat.fractionalUnit()).isEqualTo("p");
    }

}
