package com.faire.vanhackTest.metrics;

import com.faire.vanhackTest.TestConstants;
import com.faire.vanhackTest.dataObjects.Address;
import com.faire.vanhackTest.dataObjects.Order;
import com.faire.vanhackTest.dataObjects.OrderItem;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class StateWithMoreOrdersTest {

    @Test
    void stateWithMoreOrdersTest() {
        StateWithMoreOrders stateOrder = new StateWithMoreOrders();

        // Test for null value before any product option be added
        assertNull(stateOrder.getStateWithMoreOrders());

        // Test for 0 value before any product option be added
        assertEquals(0, stateOrder.getNumerOfOrders());

        Order order1 = new Order();
        order1.address = new Address();
        order1.address.state = TestConstants.TORONTO;

        stateOrder.addOrder(order1);

        assertEquals(1, stateOrder.getNumerOfOrders());
        assertEquals(TestConstants.TORONTO, stateOrder.getStateWithMoreOrders());

        order1.address.state = TestConstants.VANCOUVER;
        stateOrder.addOrder(order1);
        stateOrder.addOrder(order1);

        assertEquals(2, stateOrder.getNumerOfOrders());
        assertEquals(TestConstants.VANCOUVER, stateOrder.getStateWithMoreOrders());
    }
}