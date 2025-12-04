package services;

import models.Payment;
import models.Order;
import repositories.PaymentRepository;
import repositories.OrderRepository;

import java.util.List;

public class PaymentService {

    private PaymentRepository paymentRepository = PaymentRepository.getInstance();
    private OrderRepository orderRepository = OrderRepository.getInstance();

    public boolean processPayment(int orderId, double amount) {
        Order order = orderRepository.findById(orderId);

        if (order == null) return false;
        if (amount < order.getTotalPrice()) return false;

        Payment payment = new Payment(0, orderId, amount, "CARD", true);
        paymentRepository.save(payment);

        return true;
    }

    public boolean refundPayment(int paymentId) {
        Payment payment = paymentRepository.findById(paymentId);
        if (payment == null) return false;

        payment.setSuccess(false);
        return true;
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }
}
