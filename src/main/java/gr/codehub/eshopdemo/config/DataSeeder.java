package gr.codehub.eshopdemo.config;

import com.github.javafaker.Faker;
import gr.codehub.eshopdemo.model.Customer;
import gr.codehub.eshopdemo.model.Order;
import gr.codehub.eshopdemo.model.Product;
import gr.codehub.eshopdemo.repository.CustomerRepository;
import gr.codehub.eshopdemo.repository.OrderRepository;
import gr.codehub.eshopdemo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final Faker faker = new Faker();
    private final Random random = new Random();

    @Override
    public void run(String... args) {
        System.out.println(" Checking and Seeding Data...");

        if (customerRepository.count() == 0) {
            seedCustomers();
            System.out.println(" Customers Seeded");
        } else {
            System.out.println(" Customers already exist. Skipping...");
        }

        if (productRepository.count() == 0) {
            seedProducts();
            System.out.println(" Products Seeded");
        } else {
            System.out.println(" Products already exist. Skipping...");
        }

        if (orderRepository.count() == 0) {
            seedOrders();
            System.out.println(" Orders Seeded");
        } else {
            System.out.println(" Orders already exist. Skipping...");
        }
    }

    private void seedCustomers() {
        List<Customer> customers = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Customer customer = new Customer();
            customer.setName(faker.name().fullName());
            customer.setEmail(faker.internet().emailAddress());
            customer.setAddress(faker.address().fullAddress());
            customers.add(customer);
        }
        customerRepository.saveAll(customers);
        System.out.println(" Customers Seeded ");
    }


    private void seedProducts() {
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            products.add(new Product(
                    null,
                    faker.commerce().productName(),
                    faker.number().randomDouble(2, 5, 500)
            ));
        }
        productRepository.saveAll(products);
    }

    private void seedOrders() {
        List<Customer> customers = customerRepository.findAll();
        List<Product> products = productRepository.findAll();
        List<Order> orders = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            Customer customer = customers.get(random.nextInt(customers.size()));
            int productCount = random.nextInt(3) + 1; // Ensures at least 1 product
            List<Product> orderProducts = products.subList(0, Math.min(productCount, products.size()));

            Order order = new Order();
            order.setCustomer(customer);
            order.setProducts(orderProducts);
            order.setStatus(faker.options().option("Pending", "Shipped", "Delivered"));

            orders.add(order);
        }
        orderRepository.saveAll(orders);
    }
}
