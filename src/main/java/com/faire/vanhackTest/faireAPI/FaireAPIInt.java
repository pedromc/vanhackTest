package com.faire.vanhackTest.faireAPI;


import com.faire.vanhackTest.dataObjects.Order;
import com.faire.vanhackTest.dataObjects.Product;
import com.faire.vanhackTest.dataObjects.ProductOptionUpdateValues;

import java.util.List;

public interface FaireAPIInt {

    List<Product> getAllProducts(int page);
    List<Order> getAllOrders(int page);
    void backorderingItems(String id);
    void acceptOrder(String id);
    void updateProdutOptions(List<ProductOptionUpdateValues> values);
}