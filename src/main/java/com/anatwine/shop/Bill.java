package com.anatwine.shop;

import java.util.List;

public class Bill {

    private Double subtotal;
    private Double total;
    private List<String> messages;

    public Bill(Double subtotal,  Double total, List<String> messages) {
        this.subtotal = subtotal;
        this.total = total;
        this.messages = messages;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public Double getTotal() {
        return total;
    }

    public List<String> getMessages() {
        return messages;
    }

}
