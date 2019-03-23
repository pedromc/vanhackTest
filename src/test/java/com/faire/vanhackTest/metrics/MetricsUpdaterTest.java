package com.faire.vanhackTest.metrics;

import com.faire.vanhackTest.TestConstants;
import com.faire.vanhackTest.dataObjects.Address;
import com.faire.vanhackTest.dataObjects.Order;
import com.faire.vanhackTest.dataObjects.OrderItem;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MetricsUpdaterTest {

    @Test
    void testMetrics() {

        MetricsUpdater metrics = new MetricsUpdater();
        BestSellingProductOption bestSelling = new BestSellingProductOption();
        BiggestOrder biggestOrder = new BiggestOrder();
        LargestOrder largestOrder = new LargestOrder();
        ProductItemInMoreOrders prodItem = new ProductItemInMoreOrders();
        StateWithMoreOrders stateMoreOrders = new StateWithMoreOrders();


        metrics.addMetric(bestSelling);
        metrics.addMetric(biggestOrder);
        metrics.addMetric(largestOrder);
        metrics.addMetric(prodItem);
        metrics.addMetric(stateMoreOrders);

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

        metrics.addOrderItem(o1);
        metrics.addOrderItem(o2);
        metrics.addOrderItem(o3);

        Order order1 = new Order();
        order1.address = new Address();
        order1.address.state = TestConstants.TORONTO;
        order1.items = new ArrayList<>();
        order1.items.add(o1);
        order1.items.add(o2);
        order1.items.add(o3);

        metrics.addOrder(order1);

        assertEquals(20, bestSelling.getBestSellingProductOptionQuantity());
        assertEquals(TestConstants.PRODUCT_OPTION_ID_2, bestSelling.getBestSellingProductOptionId());

        assertEquals(45, biggestOrder.getNumerOfProductsOptions());
        assertEquals(order1, biggestOrder.getBiggestOrder());

        assertEquals(90, largestOrder.getLargestAmount());
        assertEquals(order1, largestOrder.getLargestOrder());

        assertEquals(1, stateMoreOrders.getNumerOfOrders());
        assertEquals(TestConstants.TORONTO, stateMoreOrders.getStateWithMoreOrders());

        o1.quantity = 50;
        metrics.addOrderItem(o1);
        metrics.addOrderItem(o2);
        metrics.addOrderItem(o3);
        order1.address.state = TestConstants.VANCOUVER;

        metrics.addOrder(order1);
        metrics.addOrder(order1);

        assertEquals(60, bestSelling.getBestSellingProductOptionQuantity());
        assertEquals(TestConstants.PRODUCT_OPTION_ID_1, bestSelling.getBestSellingProductOptionId());

        assertEquals(85, biggestOrder.getNumerOfProductsOptions());
        assertEquals(order1, biggestOrder.getBiggestOrder());

        assertEquals(170, largestOrder.getLargestAmount());
        assertEquals(order1, largestOrder.getLargestOrder());

        assertEquals(2, stateMoreOrders.getNumerOfOrders());
        assertEquals(TestConstants.VANCOUVER, stateMoreOrders.getStateWithMoreOrders());


    }
}