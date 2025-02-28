package gr.codehub.eshopdemo.service;

import gr.codehub.eshopdemo.model.Order;
import java.util.List;

public interface OrderService {
    List<Order> getAllOrders();
    Order getOrderById(Long id);
    Order saveOrder(Order order);
    void deleteOrder(Long id);
    Order updateOrder(Long id, Order updatedOrder);
    List<Order> getOrdersByCustomerId(Long customerId);


}
