package gr.codehub.eshopdemo.domain.repository;

import gr.codehub.eshopdemo.domain.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
    @Query("SELECT c FROM Customer c WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :namePattern, '%'))")
    List<Customer> findCustomersByNamePattern(@Param("namePattern") String namePattern);
    // Custom SQL query to find customers with complex conditions
    @Query(value =
            "SELECT * FROM customer " +
                    "WHERE LOWER(name) LIKE LOWER(CONCAT('%', :namePattern, '%')) " +
                    "AND (address IS NOT NULL AND LOWER(address) LIKE LOWER(CONCAT('%', :addressPattern, '%'))) " +
                    "AND created_at >= :startDate " +
                    "ORDER BY created_at DESC",
            nativeQuery = true)
    List<Customer> findCustomersWithComplexConditions(
            @Param("namePattern") String namePattern,
            @Param("addressPattern") String addressPattern,
            @Param("startDate") LocalDateTime startDate
    );

} 