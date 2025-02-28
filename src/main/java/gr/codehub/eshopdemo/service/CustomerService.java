package gr.codehub.eshopdemo.service;

import gr.codehub.eshopdemo.model.Customer;
import java.util.List;

public interface CustomerService {
    List<Customer> getAllCustomers();
    Customer getCustomerById(Long id);
    Customer saveCustomer(Customer customer);
    void deleteCustomer(Long id);
    Customer updateCustomer(Long id, Customer updatedCustomer);
    Customer getCustomerByEmail(String email);


}
