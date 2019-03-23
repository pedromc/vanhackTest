package com.faire.vanhackTest.metrics;

import com.faire.vanhackTest.TestConstants;
import com.faire.vanhackTest.dataObjects.OrderItem;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ProductItemInMoreOrdersTest {

    @Test
    void productItemInMoreOrderTest() {

        ProductItemInMoreOrders prodItemMoreOrder = new ProductItemInMoreOrders();

        // Test for null value before any product option be added
        assertNull(prodItemMoreOrder.getProductItemInMoreOrdersId());

        // Test for 0 value before any product option be added
        assertEquals(0, prodItemMoreOrder.getNumerOfOrders());

        OrderItem o1 = new OrderItem();
        OrderItem o2 = new OrderItem();
        OrderItem o3 = new OrderItem();

        o1.product_option_id = TestConstants.PRODUCT_OPTION_ID_1;
        o2.product_option_id = TestConstants.PRODUCT_OPTION_ID_2;
        o3.product_option_id = TestConstants.PRODUCT_OPTION_ID_3;


        prodItemMoreOrder.addOrderItem(o1);

        assertEquals(1, prodItemMoreOrder.getNumerOfOrders());
        assertEquals(TestConstants.PRODUCT_OPTION_ID_1, prodItemMoreOrder.getProductItemInMoreOrdersId());

        prodItemMoreOrder.addOrderItem(o2);
        prodItemMoreOrder.addOrderItem(o2);

        assertEquals(2, prodItemMoreOrder.getNumerOfOrders());
        assertEquals(TestConstants.PRODUCT_OPTION_ID_2, prodItemMoreOrder.getProductItemInMoreOrdersId());

        prodItemMoreOrder.addOrderItem(o3);


        assertEquals(2, prodItemMoreOrder.getNumerOfOrders());
        assertEquals(TestConstants.PRODUCT_OPTION_ID_2, prodItemMoreOrder.getProductItemInMoreOrdersId());

    }
}