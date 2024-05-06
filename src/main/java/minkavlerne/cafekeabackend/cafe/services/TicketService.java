package minkavlerne.cafekeabackend.cafe.services;

import minkavlerne.cafekeabackend.cafe.entity.Ticket;
import minkavlerne.cafekeabackend.cafe.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }
    public List<Ticket> getTickets(){
        return ticketRepository.findAll();
    }
}
