package models;

import java.util.Date; 

public class Order {
    private int orderId;
    private int attendeeId;
    private Date orderDate;
    private double totalAmount;

    public boolean createOrder() { return false; }
    public boolean cancelOrder() { return false; }
}