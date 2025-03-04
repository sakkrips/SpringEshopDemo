package gr.codehub.eshopdemo.repository;

import gr.codehub.eshopdemo.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByEmail(String email);
    List<Customer> findByName(String name);
    List<Customer> findByAddress(String address);
    List<Customer> findByCreatedAtBefore(LocalDateTime date);
    List<Customer> findByCreatedAtAfter(LocalDateTime date);



}
