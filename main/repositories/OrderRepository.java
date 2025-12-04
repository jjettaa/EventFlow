package repositories;

import models.Order;
import java.util.ArrayList;
import java.util.List;

public class OrderRepository {

    private static OrderRepository instance;

    private OrderRepository() {}

    public static OrderRepository getInstance() {
        if (instance == null) instance = new OrderRepository();
        return instance;
    }

    private List<Order> orders = new ArrayList<>();
    private int nextId = 1;

    public List<Order> findAll() {
        return orders;
    }

    public List<Order> findByUserId(int userId) {
        List<Order> result = new ArrayList<>();
        for (Order o : orders) {
            if (o.getUserId() == userId) result.add(o);
        }
        return result;
    }

    public Order findById(int id) {
        for (Order o : orders) {
            if (o.getOrderId() == id) return o;
        }
        return null;
    }

    public Order save(Order order) {
        order.setOrderId(nextId++);
        orders.add(order);
        return order;
    }

    public boolean deleteById(int id) {
        return orders.removeIf(o -> o.getOrderId() == id);
    }
}
