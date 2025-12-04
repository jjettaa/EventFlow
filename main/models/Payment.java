package models;

import java.time.LocalDateTime;

public class Payment {

    private int paymentId;
    private int orderId;
    private double amount;
    private String paymentMethod;
    private boolean success;
    private LocalDateTime paymentTime;

    public Payment() {}

    public Payment(int paymentId, int orderId, double amount, String paymentMethod, boolean success) {
        this.paymentId = paymentId;
        this.orderId = orderId;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.success = success;
        this.paymentTime = LocalDateTime.now();
    }

    // Getters & Setters
    public int getPaymentId() { return paymentId; }
    public void setPaymentId(int paymentId) { this.paymentId = paymentId; }

    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }

    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }

    public LocalDateTime getPaymentTime() { return paymentTime; }
}
