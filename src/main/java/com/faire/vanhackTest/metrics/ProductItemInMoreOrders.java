package com.faire.vanhackTest.metrics;

import com.faire.vanhackTest.dataObjects.Order;
import com.faire.vanhackTest.dataObjects.OrderItem;

import java.util.HashMap;
import java.util.Map;

public class ProductItemInMoreOrders implements OrderItemMetricsInterface {

    private Map<String, Integer> idQuantities;
    private String productItemInMoreOrdersId;
    private int numberOfOrders;

    public ProductItemInMoreOrders() {
        idQuantities = new HashMap<>();
        numberOfOrders = 0;
    }

    /**
     * Method to add information of a order item
     * @param order The order being processed
     * @param orderItem The order item being processed
     */
    @Override
    public void addOrderItem(OrderItem orderItem) {

        int currentQuantity = 0;
        if (idQuantities.containsKey(orderItem.product_option_id)) {
            currentQuantity += idQuantities.get(orderItem.product_option_id);
        }
        idQuantities.put(orderItem.product_option_id, currentQuantity + 1);
        if (numberOfOrders < currentQuantity + 1) {
            numberOfOrders = currentQuantity + 1;
            productItemInMoreOrdersId = orderItem.product_option_id;
        }
    }

    /**
     * @return The best selling product option. If there is more than one product with the same quantity
     *         this method will return only one of them.
     */
    public String getProductItemInMoreOrdersId() {
        return productItemInMoreOrdersId;
    }

    /**
     * @return The quantity sold of the best selling product
     */
    public int getNumerOfOrders() {
        return numberOfOrders;
    }

}
