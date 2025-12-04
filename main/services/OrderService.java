package services;

import models.Order;
import models.Ticket;
import repositories.OrderRepository;
import repositories.TicketRepository;

import java.util.List;

public class OrderService {

    private OrderRepository orderRepository = OrderRepository.getInstance();
    private TicketRepository ticketRepository = TicketRepository.getInstance();

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public List<Order> getOrdersByUser(int userId) {
        return orderRepository.findByUserId(userId);
    }

public boolean createOrder(Order order) {
    if (order == null) return false;

    Ticket ticket = ticketRepository.findById(order.getTicketId());
    if (ticket == null || ticket.isSold()) return false;

    order.setTotalPrice(ticket.getPrice()); // add this!
    ticket.setSold(true);
    ticketRepository.save(ticket);

    orderRepository.save(order);
    return true;
}

    public boolean cancelOrder(int orderId) {
        Order order = orderRepository.findById(orderId);
        if (order == null) return false;

        Ticket ticket = ticketRepository.findById(order.getTicketId());
        if (ticket != null) {
            ticket.setSold(false);
            ticketRepository.save(ticket);
        }

        return orderRepository.deleteById(orderId);
    }
}
