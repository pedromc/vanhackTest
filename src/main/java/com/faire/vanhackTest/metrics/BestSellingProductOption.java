package com.faire.vanhackTest.metrics;


import com.faire.vanhackTest.dataObjects.Order;
import com.faire.vanhackTest.dataObjects.OrderItem;
import com.faire.vanhackTest.dataObjects.ProductOption;

import java.util.HashMap;
import java.util.Map;

public class BestSellingProductOption implements OrderItemMetricsInterface {


    private Map<String, Integer> idQuantities;
    private String bestSellingId;
    private int bestSellingQuantity;

    public BestSellingProductOption() {
        idQuantities = new HashMap<>();
        bestSellingQuantity = 0;
    }

    /**
     * Method to add information of a order item
     * @param orderItem The order item being processed
     */
    @Override
    public void addOrderItem(OrderItem orderItem) {

        int currentQuantity = 0;
        if (idQuantities.containsKey(orderItem.product_option_id)) {
            currentQuantity += idQuantities.get(orderItem.product_option_id);
        }
        idQuantities.put(orderItem.product_option_id, currentQuantity + orderItem.quantity);
        if (bestSellingQuantity < currentQuantity + orderItem.quantity) {
            bestSellingQuantity = currentQuantity + orderItem.quantity;
            bestSellingId = orderItem.product_option_id;
        }
    }

    /**
     * @return The best selling product option. If there is more than one product with the same quantity
     *         this method will return only one of them.
     */
    public String getBestSellingProductOptionId() {
        return bestSellingId;
    }

    /**
     * @return The quantity sold of the best selling product
     */
    public int getBestSellingProductOptionQuantity() {
        return bestSellingQuantity;
    }
}
