package gr.codehub.eshopdemo.application.service;

import gr.codehub.eshopdemo.application.dto.CustomerDTO;
import java.time.LocalDateTime;
import java.util.List;

public interface CustomerService {
    List<CustomerDTO> getAllCustomers();
    CustomerDTO getCustomerById(Long id);
    CustomerDTO saveCustomer(CustomerDTO customerDTO);
    void deleteCustomer(Long id);
    CustomerDTO updateCustomer(Long id, CustomerDTO updatedCustomer);
    CustomerDTO getCustomerByEmail(String email);
    List<CustomerDTO> getCustomersByName(String name);
    List<CustomerDTO> getCustomersByAddress(String address);
    List<CustomerDTO> getCustomersCreatedAfter(LocalDateTime date);
    List<CustomerDTO> getCustomersCreatedBefore(LocalDateTime date);
    List<CustomerDTO> findCustomersByNamePattern(String pattern);
    List<CustomerDTO> findCustomersWithComplexConditions(String namePattern, String addressPattern, LocalDateTime startDate);
} 