package models;

import java.time.LocalDateTime;

public class Order {

    private int orderId;
    private int userId;
    private int ticketId;
    private double totalPrice;
    private LocalDateTime orderTime;

    public Order() {}

    public Order(int orderId, int userId, int ticketId, double totalPrice) {
        this.orderId = orderId;
        this.userId = userId;
        this.ticketId = ticketId;
        this.totalPrice = totalPrice;
        this.orderTime = LocalDateTime.now();
    }

    // Getters & Setters
    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public int getTicketId() { return ticketId; }
    public void setTicketId(int ticketId) { this.ticketId = ticketId; }

    public double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }

    public LocalDateTime getOrderTime() { return orderTime; }
}
