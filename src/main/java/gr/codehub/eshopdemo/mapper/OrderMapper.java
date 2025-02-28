package gr.codehub.eshopdemo.mapper;

import gr.codehub.eshopdemo.dto.OrderDTO;
import gr.codehub.eshopdemo.model.Customer;
import gr.codehub.eshopdemo.model.Order;
import gr.codehub.eshopdemo.model.Product;
import gr.codehub.eshopdemo.service.CustomerService;
import gr.codehub.eshopdemo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderMapper {
    private final CustomerService customerService;
    private final ProductService productService;

    public OrderDTO toDTO(Order order) {
        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setCustomerId(order.getCustomer().getId());
        dto.setProductIds(order.getProducts().stream().map(Product::getId).collect(Collectors.toList()));
        dto.setStatus(order.getStatus());
        return dto;
    }

    public Order toEntity(OrderDTO orderDTO) {
        Order order = new Order();
        Customer customer = customerService.getCustomerById(orderDTO.getCustomerId());
        List<Product> products = orderDTO.getProductIds().stream()
                .map(productService::getProductById)
                .collect(Collectors.toList());
        order.setCustomer(customer);
        order.setProducts(products);
        order.setStatus(orderDTO.getStatus());
        return order;
    }
}
