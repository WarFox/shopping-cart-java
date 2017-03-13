package com.anatwine.shop;

import com.anatwine.shop.models.Bill;
import com.anatwine.shop.models.Discount;
import com.anatwine.shop.models.PriceList;
import com.anatwine.shop.offers.OfferProcessor;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;

public class BillGeneratorTest {

    private static final Map<String, Double> prices = new HashMap<>();

    @BeforeClass
    public static void setUp() throws Exception {
        prices.put("Orange", 1.30);
        prices.put("Apple", 1.0);
        prices.put("Bread", 0.80);
        prices.put("Soup", .65);
        prices.put("Milk", 1.30);
        prices.put("Cake", 1.30);
    }

    @Test
    public void testGenerateBillWithOutOffers() throws Exception {
        List<String> list = new ArrayList<>();
        ShoppingCart shoppingCart = new ShoppingCart(list);
        PriceList priceList = Mockito.mock(PriceList.class);
        OfferProcessor offerProcessor = Mockito.mock(OfferProcessor.class);
        BillGenerator billGenerator = new BillGenerator(priceList, offerProcessor);
        Bill bill = billGenerator.generateBill(shoppingCart);
        assertThat(bill.getSubtotal()).isZero();
    }

    @Test
    public void testGenerateBillWithOffers() throws Exception {
        List<String> list = Arrays.asList("Apple", "Bread", "Milk", "Soup", "Soup", "Apple");
        PriceList priceList = Mockito.mock(PriceList.class);
        Mockito.when(priceList.getPrice(any())).thenReturn(1.0);

        OfferProcessor offerProcessor = Mockito.mock(OfferProcessor.class);
        Mockito.when(offerProcessor.applyOffer(any()))
                .thenReturn(Arrays.asList(
                        new Discount("item2", 10.0, 1.0),
                        new Discount("item1", 10.0, 1.0)
                ));

        BillGenerator billGenerator = new BillGenerator(priceList, offerProcessor);
        ShoppingCart shoppingCart = new ShoppingCart(list);

        Bill bill = billGenerator.generateBill(shoppingCart);
        assertThat(bill.getSubtotal()).isEqualTo(6);
        assertThat(bill.getTotal()).isEqualTo(4.0);
    }

    @Test
    public void testCalculateSubtotal() throws Exception {
        PriceList priceList = Mockito.mock(PriceList.class);
        Mockito.when(priceList.getPrice(any())).thenReturn(2.0);
        OfferProcessor offerProcessor = Mockito.mock(OfferProcessor.class);
        BillGenerator billGenerator = new BillGenerator(priceList, offerProcessor);
        HashMap<String, Long> itemCountMap = new HashMap<>();
        itemCountMap.put("item1", 2L);
        itemCountMap.put("item2", 3L);
        itemCountMap.put("item3", 1L);
        itemCountMap.put("item4", 4L);

        ShoppingCart cart = Mockito.mock(ShoppingCart.class);
        Mockito.when(cart.getItemCountMap())
                .thenReturn(itemCountMap);
        Double subTotal = billGenerator.calculateSubTotal(cart);
        assertThat(subTotal).isEqualTo(20);
    }

    @Test
    public void testCalculateSubtotal3Apples() throws Exception {
        PriceList priceList = new PriceList(prices);
        Map<String, Long> itemCountMap = new HashMap<>();
        itemCountMap.put("Apple", 3L);
        Double subTotal = BillGenerator.calculateSubTotal(itemCountMap, priceList);
        assertThat(subTotal).isEqualTo(3.0);
    }

    @Test
    public void testTotalDiscount() throws Exception {
        ArrayList<Discount> discounts = new ArrayList<>();
        discounts.add(new Discount("name", 10.0, 10.0));
        discounts.add(new Discount("name", 10.0, 20.0));
        discounts.add(new Discount("name", 10.0, 30.0));
        Optional<Double> optionalDiscount = BillGenerator.totalDiscount(discounts);
        assertThat(optionalDiscount.get()).isEqualTo(60);
    }

    @Test
    public void testTotalDiscountEmptyDiscountsList() throws Exception {
        ArrayList<Discount> discounts = new ArrayList<>();
        Optional<Double> optionalDiscount = BillGenerator.totalDiscount(discounts);
        assertThat(optionalDiscount).isEmpty();
    }

}
