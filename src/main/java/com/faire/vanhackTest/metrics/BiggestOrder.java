package com.faire.vanhackTest.metrics;

import com.faire.vanhackTest.dataObjects.Order;

public class BiggestOrder implements OrderMetricsInterface{

    private Order biggestOrder;
    private int numberOfProductOptions;

    public BiggestOrder() {
        numberOfProductOptions = 0;
        biggestOrder = null;
    }

    @Override
    public void addOrder(Order order) {

        int currentNumberOfProductOptions = order.items.stream().mapToInt( o -> o.quantity).sum();
        if (numberOfProductOptions < currentNumberOfProductOptions) {
            numberOfProductOptions = currentNumberOfProductOptions;
            biggestOrder = order;
        }

    }

    public Order getBiggestOrder() {
        return biggestOrder;
    }

    public int getNumerOfProductsOptions() {
        return numberOfProductOptions;
    }

}
