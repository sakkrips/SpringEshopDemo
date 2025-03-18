package gr.codehub.eshopdemo.application.service;

import gr.codehub.eshopdemo.application.dto.OrderDTO;
import java.util.List;

public interface OrderService {
    List<OrderDTO> getAllOrders();
    OrderDTO getOrderById(Long id);
    OrderDTO saveOrder(OrderDTO orderDTO);
    void deleteOrder(Long id);
    OrderDTO updateOrder(Long id, OrderDTO updatedOrder);
    List<OrderDTO> getOrdersByCustomerId(Long customerId);
} 