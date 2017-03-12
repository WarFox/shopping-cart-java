package com.anatwine.shop;

import com.anatwine.shop.models.Bill;
import com.anatwine.shop.models.DiscountItem;
import com.anatwine.shop.models.OfferItem;
import com.anatwine.shop.models.PriceList;
import com.anatwine.shop.offers.OfferProcessor;
import com.anatwine.shop.utils.CurrencyFormat;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import java.util.*;

/**
 * Main class
 */
public class App {

    public static void main(String[] args) {
        List<String> items = Arrays.asList(args);

        ShoppingCart shoppingCart = new ShoppingCart(items);

        Config config = ConfigFactory.load();
        PriceList priceList = createPriceList(config);

        OfferProcessor offerProcessor = OfferProcessor.createSimpleOfferProcessor(getOffers(), priceList);

        BillGenerator billGenerator = new BillGenerator(priceList, offerProcessor);
        Bill bill = billGenerator.generateBill(shoppingCart);
        printBill(bill);
    }

    // At the moment this is static
    // But can be loaded from the config file
    private static List<OfferItem> getOffers() {
        List<OfferItem> offerItems = new ArrayList<>();
        offerItems.add(new OfferItem("Apple", 1, new DiscountItem("Apple", 10.0)));
        offerItems.add(new OfferItem("Soup", 2, new DiscountItem("Bread", 50.0)));
        offerItems.add(new OfferItem("Milk", 1, new DiscountItem("Milk", 50.0)));
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


    private static void printBill(Bill bill) {
        CurrencyFormat currencyFormat = new CurrencyFormat(Locale.UK);
        System.out.println("Subtotal: " + currencyFormat.format(bill.getSubtotal()));
        bill.getDiscounts().forEach(discount -> {
            System.out.println(discount.getName() + " " + discount.getDiscountPercent() + "% off: -" + currencyFormat.format(discount.getDiscountValue()));
        });
        if (bill.getDiscounts().size() == 0) {
            System.out.println("(No offers available)");
        }
        System.out.println("Total: " + currencyFormat.format(bill.getTotal()));
    }

}
