package com.anatwine.shop;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * This is a very simple data structure that wraps a list of items
 * that is being purchased
 */
public class ShoppingCart {

    private final List<String> items;
    private Map<String, Long> itemCountMap;

    public ShoppingCart(List<String> items) {
        this.items = items;
    }

    public Map<String, Long> getItemCountMap() {
        if (itemCountMap == null) {
            itemCountMap = getItems().stream()
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        }
        return itemCountMap;
    }

    public List<String> getItems() {
        return items;
    }

    public Long getItemCount(String item) {
        Long count = getItemCountMap().get(item);
        return count == null ? 0 : count;
    }

}
