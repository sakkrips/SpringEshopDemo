package gr.codehub.eshopdemo.controller;

import gr.codehub.eshopdemo.dto.CustomerDTO;
import gr.codehub.eshopdemo.mapper.CustomerMapper;
import gr.codehub.eshopdemo.model.Customer;
import gr.codehub.eshopdemo.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

    @GetMapping
    public List<CustomerDTO> getAllCustomers() {
        return customerService.getAllCustomers().stream()
                .map(customerMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long id) {
        Customer customer = customerService.getCustomerById(id);
        return ResponseEntity.ok(customerMapper.toDTO(customer));
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDTO) {
        Customer customer = customerMapper.toEntity(customerDTO);
        Customer savedCustomer = customerService.saveCustomer(customer);
        return ResponseEntity.ok(customerMapper.toDTO(savedCustomer));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
        Customer updatedCustomer = customerService.updateCustomer(id, customerMapper.toEntity(customerDTO));
        return ResponseEntity.ok(customerMapper.toDTO(updatedCustomer));
    }

    // Find Customer by Email
    @GetMapping("/email/{email}")
    public ResponseEntity<CustomerDTO> getCustomerByEmail(@PathVariable String email) {
        Customer customer = customerService.getCustomerByEmail(email);
        return ResponseEntity.ok(customerMapper.toDTO(customer));
    }

    // Find Customers by Name
    @GetMapping("/name/{name}")
    public List<CustomerDTO> getCustomersByName(@PathVariable String name) {
        return customerService.getCustomersByName(name)
                .stream()
                .map(customerMapper::toDTO)
                .collect(Collectors.toList());
    }

    //  Find Customers by Address
    @GetMapping("/address/{address}")
    public List<CustomerDTO> getCustomersByAddress(@PathVariable String address) {
        return customerService.getCustomersByAddress(address)
                .stream()
                .map(customerMapper::toDTO)
                .collect(Collectors.toList());
    }

    //  Find Customers Created After a Specific Date
    @GetMapping("/created-after")
    public List<CustomerDTO> getCustomersCreatedAfter(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {
        return customerService.getCustomersCreatedAfter(date)
                .stream()
                .map(customerMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Find Customers Created Before a Specific Date
    @GetMapping("/created-before")
    public List<CustomerDTO> getCustomersCreatedBefore(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {
        return customerService.getCustomersCreatedBefore(date)
                .stream()
                .map(customerMapper::toDTO)
                .collect(Collectors.toList());
    }
}
