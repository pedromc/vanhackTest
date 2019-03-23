package com.faire.vanhackTest;

import com.faire.vanhackTest.dataObjects.Order;
import com.faire.vanhackTest.dataObjects.OrderItem;
import com.faire.vanhackTest.dataObjects.Product;
import com.faire.vanhackTest.dataObjects.ProductOption;
import com.faire.vanhackTest.faireAPI.FaireAPIInt;

import java.util.List;

public class ProcessOrders {

    private FaireAPIInt faireAPI;
    private Inventory inventory;

    public ProcessOrders(Inventory inventory, FaireAPIInt faireAPI) {
        this.faireAPI = faireAPI;
        this.inventory = inventory;
    }


    public void process() {
        //quantities.clear();
        int pageCount = 1;

        List<Order> pageData = faireAPI.getAllOrders(pageCount);

        while (pageData != null && !pageData.isEmpty()) {
            System.out.println("page ="+pageCount);

            for (Order itOrder : pageData) {
                boolean allProductsAvailable = true;
                boolean processOrder = true;


                //Only orders in the NEW state must be processed
                if (itOrder.state != "NEW") {
                    continue;
                }

                for (OrderItem itOrderItem : itOrder.items) {
                    //If the order contains an element not in the inventory, it is a order of other brand,
                    //so It should not be processed
                    if (!inventory.checkItemInvetory(itOrderItem.product_option_id)) {
                        processOrder = false;
                        break;
                    }

                    //If the order contains an item with low quantity in the inventory, the order should be put
                    //in the backordered state
                    if(inventory.getProductOptionAvailableQuantity(itOrderItem.product_option_id) < itOrderItem.quantity) {
                        allProductsAvailable = false;
                        break;
                    }
                }


                if(processOrder) {
                    if(!allProductsAvailable) {
                        backOrder(itOrder);
                    } else {
                        fullfillOrder(itOrder);
                    }
                }
            }
            pageCount++;
            pageData = faireAPI.getAllOrders(pageCount);
        }
    }

    private void backOrder(Order order) {
        faireAPI.backorderingItems(order.id);
    }

    private void fullfillOrder(Order order) {
        for (OrderItem itOrderItem : order.items) {
            inventory.consumeProductOption(itOrderItem.product_option_id, itOrderItem.quantity);
        }
        faireAPI.acceptOrder(order.id);
    }
}


