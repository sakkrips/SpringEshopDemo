package gr.codehub.eshopdemo.api.controller;

import gr.codehub.eshopdemo.application.dto.CustomerDTO;
import gr.codehub.eshopdemo.application.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class    CustomerController {
    private final CustomerService customerService;

    @GetMapping
    public List<CustomerDTO> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long id) {
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDTO) {
        return ResponseEntity.ok(customerService.saveCustomer(customerDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
        return ResponseEntity.ok(customerService.updateCustomer(id, customerDTO));
    }

    // Find Customer by Email
    @GetMapping("/email/{email}")
    public ResponseEntity<CustomerDTO> getCustomerByEmail(@PathVariable String email) {
        return ResponseEntity.ok(customerService.getCustomerByEmail(email));
    }

    // Find Customers by Name
    @GetMapping("/name/{name}")
    public List<CustomerDTO> getCustomersByName(@PathVariable String name) {
        return customerService.getCustomersByName(name);
    }

    //  Find Customers by Address
    @GetMapping("/address/{address}")
    public List<CustomerDTO> getCustomersByAddress(@PathVariable String address) {
        return customerService.getCustomersByAddress(address);
    }

    //  Find Customers Created After a Specific Date
    @GetMapping("/created-after")
    public List<CustomerDTO> getCustomersCreatedAfter(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {
        return customerService.getCustomersCreatedAfter(date);
    }

    // Find Customers Created Before a Specific Date
    @GetMapping("/created-before")
    public List<CustomerDTO> getCustomersCreatedBefore(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {
        return customerService.getCustomersCreatedBefore(date);
    }

    // Find Customers by Name Pattern using JPQL
    @GetMapping("/search")
    public List<CustomerDTO> findCustomersByNamePattern(@RequestParam("namePattern") String namePattern) {
        return customerService.findCustomersByNamePattern(namePattern);
    }
    @GetMapping("/complex-search")
    public List<CustomerDTO> findCustomersWithComplexConditions(
            @RequestParam("namePattern") String namePattern,
            @RequestParam("addressPattern") String addressPattern,
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate) {
        return customerService.findCustomersWithComplexConditions(namePattern, addressPattern, startDate);
    }

} 