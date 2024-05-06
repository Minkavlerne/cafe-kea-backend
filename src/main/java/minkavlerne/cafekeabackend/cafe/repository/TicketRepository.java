package minkavlerne.cafekeabackend.cafe.repository;

import minkavlerne.cafekeabackend.cafe.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {

}
