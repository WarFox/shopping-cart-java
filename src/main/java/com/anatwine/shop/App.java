package com.anatwine.shop;

import com.anatwine.shop.models.Bill;
import com.anatwine.shop.models.Discount;
import com.anatwine.shop.models.OfferItem;
import com.anatwine.shop.models.PriceList;
import com.anatwine.shop.offers.OfferProcessor;
import com.anatwine.shop.utils.ConfigParser;
import com.anatwine.shop.utils.CurrencyFormat;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * Main class
 */
public class App {

    private static final CurrencyFormat currencyFormat = new CurrencyFormat(Locale.UK);

    public static void main(String[] args) {
        List<String> items = Arrays.asList(args);
        Config config = ConfigFactory.load();

        ShoppingCart shoppingCart = new ShoppingCart(items);

        BillGenerator billGenerator = getBillGenerator(config);
        Bill bill = billGenerator.generateBill(shoppingCart);
        print(bill);
    }

    public static BillGenerator getBillGenerator(Config config) {
        ConfigParser configParser = new ConfigParser(config);
        List<OfferItem> offers = configParser.getOffers();
        PriceList priceList = configParser.getPriceList();

        OfferProcessor offerProcessor = OfferProcessor.createSimpleOfferProcessor(offers, priceList);

        return new BillGenerator(priceList, offerProcessor);
    }

    public static ShoppingCart getShoppingCart(String[] args) {
        return new ShoppingCart(Arrays.asList(args));
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
