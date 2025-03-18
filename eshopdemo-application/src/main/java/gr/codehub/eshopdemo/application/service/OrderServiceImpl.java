package gr.codehub.eshopdemo.application.service;

import gr.codehub.eshopdemo.application.dto.OrderDTO;
import gr.codehub.eshopdemo.application.mapper.OrderMapper;
import gr.codehub.eshopdemo.domain.exception.CustomerNotFoundException;
import gr.codehub.eshopdemo.domain.exception.OrderNotFoundException;
import gr.codehub.eshopdemo.domain.exception.ProductNotFoundException;
import gr.codehub.eshopdemo.domain.model.Customer;
import gr.codehub.eshopdemo.domain.model.Order;
import gr.codehub.eshopdemo.domain.model.Product;
import gr.codehub.eshopdemo.domain.repository.CustomerRepository;
import gr.codehub.eshopdemo.domain.repository.OrderRepository;
import gr.codehub.eshopdemo.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final OrderMapper orderMapper;

    @Override
    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(orderMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDTO getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order with ID " + id + " not found"));
        return orderMapper.toDTO(order);
    }

    @Override
    public OrderDTO saveOrder(OrderDTO orderDTO) {
        // Retrieve Customer
        Customer customer = customerRepository.findById(orderDTO.getCustomerId())
                .orElseThrow(() -> new CustomerNotFoundException("Customer with ID " + orderDTO.getCustomerId() + " not found"));

        // Retrieve Products
        List<Product> products = orderDTO.getProductIds().stream()
                .map(productId -> productRepository.findById(productId)
                        .orElseThrow(() -> new ProductNotFoundException("Product with ID " + productId + " not found")))
                .collect(Collectors.toList());

        // Convert DTO to Entity
        Order order = orderMapper.toEntity(orderDTO);
        order.setCustomer(customer);
        order.setProducts(products);

        Order savedOrder = orderRepository.save(order);
        return orderMapper.toDTO(savedOrder);
    }

    @Override
    public void deleteOrder(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new OrderNotFoundException("Order with ID " + id + " not found");
        }
        orderRepository.deleteById(id);
    }

    @Override
    public OrderDTO updateOrder(Long id, OrderDTO updatedOrderDTO) {
        Order existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order with ID " + id + " not found"));

        Customer customer = customerRepository.findById(updatedOrderDTO.getCustomerId())
                .orElseThrow(() -> new CustomerNotFoundException("Customer with ID " + updatedOrderDTO.getCustomerId() + " not found"));

        List<Product> products = updatedOrderDTO.getProductIds().stream()
                .map(productId -> productRepository.findById(productId)
                        .orElseThrow(() -> new ProductNotFoundException("Product with ID " + productId + " not found")))
                .collect(Collectors.toList());

        existingOrder.setCustomer(customer);
        existingOrder.setProducts(products);
        existingOrder.setStatus(updatedOrderDTO.getStatus());

        Order savedOrder = orderRepository.save(existingOrder);
        return orderMapper.toDTO(savedOrder);
    }

    @Override
    public List<OrderDTO> getOrdersByCustomerId(Long customerId) {
        return orderRepository.findByCustomerId(customerId).stream()
                .map(orderMapper::toDTO)
                .collect(Collectors.toList());
    }
} 