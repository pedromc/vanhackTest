package com.faire.vanhackTest.metrics;

import com.faire.vanhackTest.dataObjects.Order;
import com.faire.vanhackTest.dataObjects.OrderItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to update all metrics added to it.
 */
public class MetricsUpdater implements OrderItemMetricsInterface, OrderMetricsInterface {

    List<OrderItemMetricsInterface> orderItemMetrics;
    List<OrderMetricsInterface> orderMetrics;

    public MetricsUpdater() {
        orderItemMetrics = new ArrayList<>();
        orderMetrics = new ArrayList<>();
    }


    /**
     * Adds new metric of order item to the updater
     * @param newMetric New metric to be added
     */
    void addMetric(OrderItemMetricsInterface newMetric) {
        orderItemMetrics.add(newMetric);
    }

    /**
     * Adds new metric of order to the updater
     * @param newMetric New metric to be added
     */
    void addMetric(OrderMetricsInterface newMetric) {
        orderMetrics.add(newMetric);
    }

    @Override
    public void addOrderItem(OrderItem orderItem) {
        for (OrderItemMetricsInterface it : orderItemMetrics) {
            it.addOrderItem(orderItem);
        }
    }

    @Override
    public void addOrder(Order order) {
        for (OrderMetricsInterface it : orderMetrics) {
            it.addOrder(order);
        }

    }
}
