package com.faire.vanhackTest.metrics;

import com.faire.vanhackTest.TestConstants;
import com.faire.vanhackTest.dataObjects.Order;
import com.faire.vanhackTest.dataObjects.OrderItem;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class LargestOrderTest {

    @Test
    void largestOrderTest() {


        LargestOrder largestOrder = new LargestOrder();

        // Test for null value before any product option be added
        assertNull(largestOrder.getLargestOrder());

        // Test for 0 value before any product option be added
        assertEquals(0, largestOrder.getLargestAmount());

        Order order1 = new Order();
        order1.items = new ArrayList<>();

        OrderItem o1 = new OrderItem();
        OrderItem o2 = new OrderItem();
        OrderItem o3 = new OrderItem();

        o1.product_option_id = TestConstants.PRODUCT_OPTION_ID_1;
        o2.product_option_id = TestConstants.PRODUCT_OPTION_ID_2;
        o3.product_option_id = TestConstants.PRODUCT_OPTION_ID_3;

        o1.quantity = 10;
        o2.quantity = 20;
        o3.quantity = 15;
        o1.price_cents = 2;
        o2.price_cents = 2;
        o3.price_cents = 2;

        order1.items.add(o1);
        order1.items.add(o2);
        order1.items.add(o3);

        largestOrder.addOrder(order1);

        assertEquals(90, largestOrder.getLargestAmount());
        assertEquals(order1, largestOrder.getLargestOrder());


        Order order2 = new Order();
        o1.quantity = 30;
        o2.quantity = 10;
        o3.quantity = 15;
        o1.price_cents = 2;
        o2.price_cents = 2;
        o3.price_cents = 2;

        order2.items = new ArrayList<>();
        order2.items.add(o1);
        order2.items.add(o2);
        order2.items.add(o3);

        largestOrder.addOrder(order2);

        assertEquals(110, largestOrder.getLargestAmount());
        assertEquals(order2, largestOrder.getLargestOrder());
    }
}