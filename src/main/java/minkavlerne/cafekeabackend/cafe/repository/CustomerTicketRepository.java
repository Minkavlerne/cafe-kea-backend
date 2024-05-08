package minkavlerne.cafekeabackend.cafe.repository;


import minkavlerne.cafekeabackend.cafe.entity.CustomerTicket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerTicketRepository extends JpaRepository<CustomerTicket, Integer>{


    Optional<CustomerTicket> findByCustomerIdAndTicketId(int customerId, int ticketId);


}
