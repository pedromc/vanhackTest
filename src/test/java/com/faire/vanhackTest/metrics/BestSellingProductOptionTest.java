package com.faire.vanhackTest.metrics;

import com.faire.vanhackTest.TestConstants;
import com.faire.vanhackTest.dataObjects.OrderItem;
import com.faire.vanhackTest.dataObjects.ProductOption;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BestSellingProductOptionTest {


    @Test
    void addOrderInfo() {
        BestSellingProductOption bestSelling = new BestSellingProductOption();

        // Test for null value before any product option be added
        assertNull(bestSelling.getBestSellingProductOptionId());

        // Test for 0 value before any product option be added
        assertEquals(0, bestSelling.getBestSellingProductOptionQuantity());

        OrderItem o1 = new OrderItem();
        OrderItem o2 = new OrderItem();
        OrderItem o3 = new OrderItem();

        o1.product_option_id = TestConstants.PRODUCT_OPTION_ID_1;
        o2.product_option_id = TestConstants.PRODUCT_OPTION_ID_2;
        o3.product_option_id = TestConstants.PRODUCT_OPTION_ID_3;

        o1.quantity = 10;
        o2.quantity = 20;
        o3.quantity = 15;


        bestSelling.addOrderItem(o1);
        bestSelling.addOrderItem(o2);
        bestSelling.addOrderItem(o3);

        assertEquals(20, bestSelling.getBestSellingProductOptionQuantity());
        assertEquals(TestConstants.PRODUCT_OPTION_ID_2, bestSelling.getBestSellingProductOptionId());

        o1.quantity = 30;
        o2.quantity = 10;
        o3.quantity = 15;

        bestSelling.addOrderItem(o1);
        bestSelling.addOrderItem(o2);
        bestSelling.addOrderItem(o3);

        assertEquals(40, bestSelling.getBestSellingProductOptionQuantity());
        assertEquals(TestConstants.PRODUCT_OPTION_ID_1, bestSelling.getBestSellingProductOptionId());
    }

}