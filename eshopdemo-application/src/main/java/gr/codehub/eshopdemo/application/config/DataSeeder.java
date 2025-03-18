package gr.codehub.eshopdemo.application.config;

import com.github.javafaker.Faker;
import gr.codehub.eshopdemo.application.dto.CustomerDTO;
import gr.codehub.eshopdemo.application.dto.OrderDTO;
import gr.codehub.eshopdemo.application.dto.ProductDTO;
import gr.codehub.eshopdemo.application.service.CustomerService;
import gr.codehub.eshopdemo.application.service.OrderService;
import gr.codehub.eshopdemo.application.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {
    private final CustomerService customerService;
    private final ProductService productService;
    private final OrderService orderService;
    private final Faker faker = new Faker();
    private final Random random = new Random();

    @Override
    public void run(String... args) {
        System.out.println(" Checking and Seeding Data...");

        if (customerService.getAllCustomers().isEmpty()) {
            seedCustomers();
            System.out.println(" Customers Seeded");
        } else {
            System.out.println(" Customers already exist. Skipping...");
        }

        if (productService.getAllProducts().isEmpty()) {
            seedProducts();
            System.out.println(" Products Seeded");
        } else {
            System.out.println(" Products already exist. Skipping...");
        }

        if (orderService.getAllOrders().isEmpty()) {
            seedOrders();
            System.out.println(" Orders Seeded");
        } else {
            System.out.println(" Orders already exist. Skipping...");
        }
    }

    private void seedCustomers() {
        List<CustomerDTO> customers = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            CustomerDTO customerDTO = new CustomerDTO();
            customerDTO.setName(faker.name().fullName());
            customerDTO.setEmail(faker.internet().emailAddress());
            customerDTO.setAddress(faker.address().fullAddress());
            customers.add(customerDTO);
        }
        customers.forEach(customerService::saveCustomer);
        System.out.println(" Customers Seeded ");
    }

    private void seedProducts() {
        List<ProductDTO> products = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            ProductDTO productDTO = new ProductDTO();
            productDTO.setName(faker.commerce().productName());
            productDTO.setPrice(faker.number().randomDouble(2, 5, 500));
            products.add(productDTO);
        }
        products.forEach(productService::saveProduct);
    }

    private void seedOrders() {
        List<CustomerDTO> customers = customerService.getAllCustomers();
        List<ProductDTO> products = productService.getAllProducts();
        List<OrderDTO> orders = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            CustomerDTO customer = customers.get(random.nextInt(customers.size()));

            // Select a random number of products (between 1 and 3)
            int productCount = random.nextInt(3) + 1;
            List<Long> productIds = new ArrayList<>();
            for (int j = 0; j < productCount; j++) {
                productIds.add(products.get(random.nextInt(products.size())).getId());
            }

            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setCustomerId(customer.getId());
            orderDTO.setProductIds(productIds);
            orderDTO.setStatus(faker.options().option("Pending", "Shipped", "Delivered"));

            orders.add(orderDTO);
        }
        orders.forEach(orderService::saveOrder);
    }
} 