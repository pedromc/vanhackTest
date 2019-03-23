package com.faire.vanhackTest.metrics;

import com.faire.vanhackTest.dataObjects.Order;
import com.faire.vanhackTest.dataObjects.OrderItem;

public interface OrderMetricsInterface {

    /**
     * Method to add information of a order
     * @param order The order being processed
     */
    void addOrder(Order order);
}
