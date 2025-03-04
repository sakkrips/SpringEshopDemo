package gr.codehub.eshopdemo.service;

import gr.codehub.eshopdemo.model.Customer;

import java.time.LocalDateTime;
import java.util.List;

public interface CustomerService {
    List<Customer> getAllCustomers();
    Customer getCustomerById(Long id);
    Customer saveCustomer(Customer customer);
    void deleteCustomer(Long id);
    Customer updateCustomer(Long id, Customer updatedCustomer);
    Customer getCustomerByEmail(String email);
    List<Customer> getCustomersByName(String name);
    List<Customer> getCustomersByAddress(String address);
    List<Customer> getCustomersCreatedAfter(LocalDateTime date);
    List<Customer> getCustomersCreatedBefore(LocalDateTime date);
}
