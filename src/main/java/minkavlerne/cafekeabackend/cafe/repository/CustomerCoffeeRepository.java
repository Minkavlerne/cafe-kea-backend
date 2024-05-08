package minkavlerne.cafekeabackend.cafe.repository;

import minkavlerne.cafekeabackend.cafe.entity.CustomerCoffee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerCoffeeRepository extends JpaRepository<CustomerCoffee, Integer> {
    Optional<CustomerCoffee> findByCustomerIdAndId(int customerId, int id);
}
