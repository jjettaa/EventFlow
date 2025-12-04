package services;

import models.Payment;
import models.Order;
import models.Ticket;
import models.Event;
import repositories.PaymentRepository;
import repositories.OrderRepository;
import repositories.TicketRepository;
import repositories.EventRepository;

import java.util.List;

public class PaymentService {

    private PaymentRepository paymentRepository = PaymentRepository.getInstance();
    private OrderRepository orderRepository = OrderRepository.getInstance();
    private TicketRepository ticketRepository = TicketRepository.getInstance();
    private EventRepository eventRepository = EventRepository.getInstance();

    public boolean processPayment(int orderId, double amount) {
        Order order = orderRepository.findById(orderId);
        if (order == null) {
            System.out.println("Order not found.");
            return false;
        }

        if (amount < order.getTotalPrice()) {
            System.out.println("Payment amount is less than total price.");
            return false;
        }

        // Find the ticket linked to this order
        Ticket ticket = ticketRepository.findById(order.getTicketId());
        if (ticket == null) {
            System.out.println("Ticket not found for this order.");
            return false;
        }

        // Find the event for that ticket
        Event event = eventRepository.findById(ticket.getEventId());
        if (event != null && event.isCancelled()) {
            System.out.println("Payment cannot be processed because the event has been cancelled.");
            return false;
        }

        // Process successful payment
        Payment payment = new Payment(0, orderId, amount, "CARD", true);
        paymentRepository.save(payment);

        // Mark the ticket as sold
        if (!ticket.isSold()) {
            ticket.setSold(true);
        }

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
