package services;

import java.util.List;
import models.Payment;
import repositories.PaymentRepository;

public class PaymentService {

    public boolean processPayment(int orderId, double amount) { return false; }

    public boolean refundPayment(int paymentId) { return false; }

    public List<Payment> getAllPayments() { return null; }
}