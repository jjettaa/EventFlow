package repositories;

import java.util.List;
import models.Order;

public interface OrderRepository {
    Order findById(int id);
    List<Order> findByAttendeeId(int attendeeId);
    boolean save(Order order);
    boolean cancel(int id);
}