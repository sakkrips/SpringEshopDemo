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
        System.out.println("⚠️ Deleting old data...");
        orderRepository.deleteAll();
        productRepository.deleteAll();
        customerRepository.deleteAll();

        System.out.println("🔄 Seeding new data...");
        seedCustomers();
        seedProducts();
        seedOrders();

        System.out.println("✅ Database has been reset and seeded!");
    }


    private void seedCustomers() {
        List<Customer> customers = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            customers.add(new Customer(
                    null,
                    faker.name().fullName(),
                    faker.internet().emailAddress(),
                    faker.address().fullAddress()
            ));
        }
        customerRepository.saveAll(customers);
        System.out.println("✅ Customers Seeded");
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
        System.out.println("✅ Products Seeded");
    }

    private void seedOrders() {
        List<Customer> customers = customerRepository.findAll();
        List<Product> products = productRepository.findAll();
        List<Order> orders = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            Customer customer = customers.get(random.nextInt(customers.size()));
            List<Product> orderProducts = products.subList(0, random.nextInt(products.size()));

            Order order = new Order();
            order.setCustomer(customer);
            order.setProducts(orderProducts);
            order.setStatus(faker.options().option("Pending", "Shipped", "Delivered"));

            orders.add(order);
        }
        orderRepository.saveAll(orders);
        System.out.println("✅ Orders Seeded");
    }
}
