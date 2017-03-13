package com.anatwine.shop;

import com.anatwine.shop.models.*;
import com.anatwine.shop.offers.OfferProcessor;
import com.anatwine.shop.utils.CurrencyFormat;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import java.util.*;

/**
 * Main class
 */
public class App {

    private static final CurrencyFormat currencyFormat = new CurrencyFormat(Locale.UK);

    public static void main(String[] args) {
        List<String> items = Arrays.asList(args);

        ShoppingCart shoppingCart = new ShoppingCart(items);

        Config config = ConfigFactory.load();
        PriceList priceList = createPriceList(config);

        OfferProcessor offerProcessor = OfferProcessor.createSimpleOfferProcessor(getOffers(), priceList);

        BillGenerator billGenerator = new BillGenerator(priceList, offerProcessor);
        Bill bill = billGenerator.generateBill(shoppingCart);
        print(bill);
    }

    public static ShoppingCart getShoppingCart(String[] args) {
        return new ShoppingCart(Arrays.asList(args));
    }

    // At the moment this is static
    // But can be loaded from the config file
    public static List<OfferItem> getOffers() {
        List<OfferItem> offerItems = new ArrayList<>();
        offerItems.add(new OfferItem("Apple", 1, new DiscountItem("Apple", 10.0)));
        offerItems.add(new OfferItem("Soup", 2, new DiscountItem("Bread", 50.0)));
        // offerItems.add(new OfferItem("Milk", 1, new DiscountItem("Milk", 50.0)));
        return offerItems;
    }

    public static PriceList createPriceList(Config config) {
        Map<String, Double> prices = new HashMap<>();
        Config priceConfig = config.getConfig("price");
        priceConfig.entrySet().forEach(price -> {
            String product = price.getKey();
            Double value = priceConfig.getDouble(product);
            prices.put(product, value);
        });
        return new PriceList(prices);
    }

    public static void print(Bill bill) {
        System.out.println("Subtotal: " + currencyFormat.format(bill.getSubtotal()));

        bill.getDiscounts().forEach(App::print);

        if (bill.getDiscounts().isEmpty()) {
            System.out.println("(No offers available)");
        }

        System.out.println("Total: " + currencyFormat.format(bill.getTotal()));
    }

    public static void print(Discount discount) {
        System.out.println(discount.getName() + " " + discount.getDiscountPercent() + "% off: -" + currencyFormat.format(discount.getDiscountValue()));
    }

}
