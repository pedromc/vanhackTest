package com.faire.vanhackTest.metrics;

import com.faire.vanhackTest.dataObjects.Order;

import java.util.Map;

public class LargestOrder implements OrderMetricsInterface {

    private Order orderIdWithLargestAmount;
    private int largestAmount;


    public LargestOrder() {
        largestAmount = 0;
        orderIdWithLargestAmount = null;
    }

    @Override
    public void addOrder(Order order) {

        int orderValue = order.items.stream().mapToInt( o -> o.quantity*o.price_cents).sum();

        if (largestAmount < orderValue) {
            largestAmount = orderValue;
            orderIdWithLargestAmount = order;
        }

    }

    public Order getLargestOrder() {
        return orderIdWithLargestAmount;
    }

    public int getLargestAmount() {
        return largestAmount;
    }
}
