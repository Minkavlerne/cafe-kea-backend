package minkavlerne.cafekeabackend.cafe.repository;

import minkavlerne.cafekeabackend.cafe.entity.CustomerCoffee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerCoffeeRepository extends JpaRepository<CustomerCoffee, Integer> {
}
