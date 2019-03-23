package com.faire.vanhackTest.dataObjects;


import java.util.List;

public class Order {
    public String id;
    public String state;
    public String ship_after;
    public String created_at;
    public String updated_at;
    public List<OrderItem> items;
    public Address address;
    public List<Shipment> shipments;
}
