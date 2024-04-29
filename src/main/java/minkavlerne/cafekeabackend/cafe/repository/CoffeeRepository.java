package minkavlerne.cafekeabackend.cafe.repository;

import minkavlerne.cafekeabackend.cafe.entity.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeRepository extends JpaRepository<Coffee, Integer> {


}
