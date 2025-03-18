package gr.codehub.eshopdemo.application.service;

import gr.codehub.eshopdemo.application.dto.CustomerDTO;
import gr.codehub.eshopdemo.application.mapper.CustomerMapper;
import gr.codehub.eshopdemo.domain.exception.CustomerNotFoundException;
import gr.codehub.eshopdemo.domain.model.Customer;
import gr.codehub.eshopdemo.domain.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll().stream()
                .map(customerMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer with ID " + id + " not found"));
        return customerMapper.toDTO(customer);
    }

    @Override
    public CustomerDTO saveCustomer(CustomerDTO customerDTO) {
        Customer customer = customerMapper.toEntity(customerDTO);
        Customer savedCustomer = customerRepository.save(customer);
        return customerMapper.toDTO(savedCustomer);
    }

    @Override
    public void deleteCustomer(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new CustomerNotFoundException("Customer with ID " + id + " not found");
        }
        customerRepository.deleteById(id);
    }

    @Override
    public CustomerDTO updateCustomer(Long id, CustomerDTO updatedCustomerDTO) {
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer with ID " + id + " not found"));

        existingCustomer.setName(updatedCustomerDTO.getName());
        existingCustomer.setEmail(updatedCustomerDTO.getEmail());
        existingCustomer.setAddress(updatedCustomerDTO.getAddress());

        Customer savedCustomer = customerRepository.save(existingCustomer);
        return customerMapper.toDTO(savedCustomer);
    }

    @Override
    public CustomerDTO getCustomerByEmail(String email) {
        Customer customer = customerRepository.findByEmail(email)
                .orElseThrow(() -> new CustomerNotFoundException("Customer with email " + email + " not found"));
        return customerMapper.toDTO(customer);
    }

    @Override
    public List<CustomerDTO> getCustomersByName(String name) {
        List<Customer> customers = customerRepository.findByName(name);
        if (customers.isEmpty()) {
            throw new CustomerNotFoundException("No customers found with name: " + name);
        }
        return customers.stream().map(customerMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<CustomerDTO> getCustomersByAddress(String address) {
        List<Customer> customers = customerRepository.findByAddress(address);
        if (customers.isEmpty()) {
            throw new CustomerNotFoundException("No customers found at address: " + address);
        }
        return customers.stream().map(customerMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<CustomerDTO> getCustomersCreatedAfter(LocalDateTime date) {
        List<Customer> customers = customerRepository.findByCreatedAtAfter(date);
        if (customers.isEmpty()) {
            throw new CustomerNotFoundException("No customers created after: " + date);
        }
        return customers.stream().map(customerMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<CustomerDTO> getCustomersCreatedBefore(LocalDateTime date) {
        List<Customer> customers = customerRepository.findByCreatedAtBefore(date);
        if (customers.isEmpty()) {
            throw new CustomerNotFoundException("No customers created before: " + date);
        }
        return customers.stream().map(customerMapper::toDTO).collect(Collectors.toList());
    }
    @Override
    public List<CustomerDTO> findCustomersByNamePattern(String pattern) {
        return customerRepository.findCustomersByNamePattern(pattern)
                .stream()
                .map(customerMapper::toDTO)
                .collect(Collectors.toList());
    }
    @Override
    public List<CustomerDTO> findCustomersWithComplexConditions(String namePattern, String addressPattern, LocalDateTime startDate) {
        List<Customer> customers = customerRepository.findCustomersWithComplexConditions(namePattern, addressPattern, startDate);
        if (customers.isEmpty()) {
            throw new CustomerNotFoundException("No customers found matching the specified criteria");
        }
        return customers.stream().map(customerMapper::toDTO).collect(Collectors.toList());
    }
} 