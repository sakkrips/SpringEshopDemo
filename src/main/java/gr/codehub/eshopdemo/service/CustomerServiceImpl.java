package gr.codehub.eshopdemo.service;

import gr.codehub.eshopdemo.exception.CustomerNotFoundException;
import gr.codehub.eshopdemo.model.Customer;
import gr.codehub.eshopdemo.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer with ID " + id + " not found"));
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new CustomerNotFoundException("Customer with ID " + id + " not found");
        }
        customerRepository.deleteById(id);
    }

    @Override
    public Customer updateCustomer(Long id, Customer updatedCustomer) {
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer with ID " + id + " not found"));

        existingCustomer.setName(updatedCustomer.getName());
        existingCustomer.setEmail(updatedCustomer.getEmail());
        existingCustomer.setAddress(updatedCustomer.getAddress());

        return customerRepository.save(existingCustomer);
    }

    @Override
    public Customer getCustomerByEmail(String email) {
        return customerRepository.findByEmail(email)
                .orElseThrow(() -> new CustomerNotFoundException("Customer with email " + email + " not found"));
    }

    @Override
    public List<Customer> getCustomersByName(String name) {
        List<Customer> customers = customerRepository.findByName(name);
        if (customers.isEmpty()) {
            throw new CustomerNotFoundException("No customers found with name: " + name);
        }
        return customers;
    }

    @Override
    public List<Customer> getCustomersByAddress(String address) {
        List<Customer> customers = customerRepository.findByAddress(address);
        if (customers.isEmpty()) {
            throw new CustomerNotFoundException("No customers found at address: " + address);
        }
        return customers;
    }

    @Override
    public List<Customer> getCustomersCreatedAfter(LocalDateTime date) {
        List<Customer> customers = customerRepository.findByCreatedAtAfter(date);
        if (customers.isEmpty()) {
            throw new CustomerNotFoundException("No customers created after: " + date);
        }
        return customers;
    }

    @Override
    public List<Customer> getCustomersCreatedBefore(LocalDateTime date) {
        List<Customer> customers = customerRepository.findByCreatedAtBefore(date);
        if (customers.isEmpty()) {
            throw new CustomerNotFoundException("No customers created before: " + date);
        }
        return customers;
    }
}
