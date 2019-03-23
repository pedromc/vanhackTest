package com.faire.vanhackTest.metrics;

import com.faire.vanhackTest.dataObjects.Order;
import com.faire.vanhackTest.dataObjects.OrderItem;

import java.util.HashMap;
import java.util.Map;

public class StateWithMoreOrders implements OrderMetricsInterface {

    private Map<String, Integer> idQuantities;
    private String stateWithMoreOrders;
    private int ordersOfState;

    public StateWithMoreOrders() {
        idQuantities = new HashMap<>();
        ordersOfState = 0;
    }

    /**
     * Method to add information of a new order
     * @param order The order being processed
     */
    @Override
    public void addOrder(Order order) {

        if (order.address == null) {
            return;
        }

        String currentState = order.address.state;
        int quantityOfCurrentState = 0;
        if (idQuantities.containsKey(currentState)) {
            quantityOfCurrentState = idQuantities.get(currentState);
        }
        quantityOfCurrentState++;
        idQuantities.put(currentState, quantityOfCurrentState);
        if (ordersOfState < quantityOfCurrentState) {
            ordersOfState = quantityOfCurrentState;
            stateWithMoreOrders = currentState;
        }
    }

    /**
     * @return The best selling product option. If there is more than one product with the same quantity
     *         this method will return only one of them.
     */
    public String getStateWithMoreOrders() {
        return stateWithMoreOrders;
    }

    /**
     * @return The quantity sold of the best selling product
     */
    public int getNumerOfOrders() {
        return ordersOfState;
    }
}
