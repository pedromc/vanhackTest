package com.faire.vanhackTest.faireAPI;

import com.faire.vanhackTest.Constants;
import com.faire.vanhackTest.dataObjects.*;

import java.util.ArrayList;
import java.util.List;

public class FaireAPIStub implements FaireAPIInt{

    public static final String BRAND_EVEN = "Brand even";
    public static final String BRAND_ODD = "Brand odd";

    public static final String ONTARIO = "Ontario";
    public static final String VANCOUVER = "Vancouver";

    public List<String> idOrdersToAccept;
    public List<String> idOrdersToBackorder;
    public List<ProductOptionUpdateValues> productsOptionsToUpdate;


    public FaireAPIStub() {
        this.idOrdersToAccept = new ArrayList<>();
        this.idOrdersToBackorder = new ArrayList<>();
        this.productsOptionsToUpdate = new ArrayList<>();
    }

    @Override
    public List<Product> getAllProducts(int page) {
        if (page != 1) {
            return null;
        }

        List<Product> ret = new ArrayList<Product>();

        for (int prodCount = 1; prodCount<=5; prodCount++) {
            List<ProductOption> prodOptions = new ArrayList<ProductOption>();
            for (int prodOpCount = 0; prodOpCount <= 5;prodOpCount++){
                ProductOption po = new ProductOption("po_"+prodOpCount + "-p_"+prodCount);
                po.active = true;
                po.available_quantity = 10;
                po.product_id = "p_"+prodCount;
                po.name = "ProdOpt "+prodOpCount;
                prodOptions.add(po);
            }

            Product prod = new Product();
            prod.id = "p_1";
            prod.brand_id = prodCount%2 == 0? BRAND_EVEN : BRAND_ODD;
            prod.options = prodOptions;

            ret.add(prod);
        }

        return ret;
    }

    @Override
    public List<Order> getAllOrders(int page) {
        if (page != 1) {
            return null;
        }

        List<Order> ret = new ArrayList<Order>();

        for (int orderCount = 1; orderCount<=5; orderCount++) {
            List<OrderItem> orderItems = new ArrayList<OrderItem>();
            for (int orderItemsCount = 0; orderItemsCount <= 5; orderItemsCount++){
                OrderItem oi = new OrderItem();
                oi.product_option_id = "po_" + orderItemsCount + "-p_1";
                oi.quantity=4;
                orderItems.add(oi);
            }

            Order order = new Order();
            order.id = "p_1";
            order.state = Constants.ORDER_STATUS_NEW;
            order.items = orderItems;


            order.address = new Address();
            order.address.state = orderCount<3 ? VANCOUVER : ONTARIO;

            ret.add(order);
        }

        return ret;
    }

    @Override
    public void backorderingItems(String id) {
        idOrdersToBackorder.add(id);
    }

    @Override
    public void acceptOrder(String id) {
        idOrdersToAccept.add(id);
    }

    @Override
    public void updateProdutOptions(List<ProductOptionUpdateValues> values) {
        productsOptionsToUpdate.addAll(values);
    }
}
