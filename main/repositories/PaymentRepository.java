package repositories;

import models.Payment;
import java.util.ArrayList;
import java.util.List;

public class PaymentRepository {

    private static PaymentRepository instance;

    private PaymentRepository() {}

    public static PaymentRepository getInstance() {
        if (instance == null) instance = new PaymentRepository();
        return instance;
    }

    private List<Payment> payments = new ArrayList<>();
    private int nextId = 1;

    public List<Payment> findAll() {
        return payments;
    }

    public Payment findById(int id) {
        for (Payment p : payments) {
            if (p.getPaymentId() == id) return p;
        }
        return null;
    }

    public Payment save(Payment payment) {
        payment.setPaymentId(nextId++);
        payments.add(payment);
        return payment;
    }
}

