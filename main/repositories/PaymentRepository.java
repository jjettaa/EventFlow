public interface PaymentRepository {
    Payment findByOrderId(int orderId);
    boolean processPayment(Payment payment);
    List<Payment> findAll();
}