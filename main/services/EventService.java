package services;

import models.Event;
import models.Ticket;
import models.Order;
import models.Payment;
import repositories.EventRepository;
import repositories.TicketRepository;
import repositories.OrderRepository;
import repositories.PaymentRepository;

import java.util.ArrayList;
import java.util.List;

public class EventService {

    private EventRepository eventRepository = EventRepository.getInstance();
    private TicketRepository ticketRepository = TicketRepository.getInstance();
    private OrderRepository orderRepository = OrderRepository.getInstance();
    private PaymentRepository paymentRepository = PaymentRepository.getInstance();

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Event getEventById(int id) {
        return eventRepository.findById(id);
    }

    public boolean createEvent(Event event) {
        if (event == null) return false;
        eventRepository.save(event);
        return true;
    }

    public boolean cancelEvent(int eventId) {
        Event event = eventRepository.findById(eventId);
        if (event == null) return false;

        // Mark the event as cancelled
        event.setCancelled(true);

        // Cancel all tickets for this event
        List<Ticket> tickets = ticketRepository.findByEventId(eventId);
        for (Ticket t : tickets) {
            t.setCancelled(true);
        }

        // Find orders that include these tickets
        List<Order> allOrders = orderRepository.findAll();
        List<Integer> affectedOrderIds = new ArrayList<>();

        for (Order o : allOrders) {
            for (Ticket t : tickets) {
                if (o.getTicketId() == t.getTicketId()) {
                    affectedOrderIds.add(o.getOrderId());
                    break;
                }
            }
        }

        // Refund payments for those orders
        List<Payment> allPayments = paymentRepository.findAll();
        for (Payment p : allPayments) {
            if (affectedOrderIds.contains(p.getOrderId()) && p.isSuccess()) {
                p.setSuccess(false);
            }
        }

        return true;
    }
}
