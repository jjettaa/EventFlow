package models;

import java.util.Date; 

public class Payment {
    private int paymentId;
    private int orderId;
    private Date paymentDate;
    private double amount;
    private String paymentMethod;

    public boolean processPayment() { return false; }
    public boolean refundPayment() { return false; }
}