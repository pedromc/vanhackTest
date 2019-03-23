package com.faire.vanhackTest.metrics;

import com.faire.vanhackTest.dataObjects.OrderItem;

public interface OrderItemMetricsInterface {
    /**
     * Method to add information of a order item
     * @param orderItem The order item being processed
     */
    void addOrderItem(OrderItem orderItem);

}
