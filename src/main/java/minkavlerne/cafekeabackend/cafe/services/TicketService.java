package minkavlerne.cafekeabackend.cafe.services;

import minkavlerne.cafekeabackend.cafe.dto.TicketDto;
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
    public List<TicketDto> getTickets(){
        List<Ticket> tickets = ticketRepository.findAll();
        return tickets.stream().map(TicketDto::new).toList();
    }
    public TicketDto addTicket(TicketDto ticket){
        Ticket newTicket = new Ticket();
        newTicket.setName(ticket.getName());
        newTicket.setPrice(ticket.getPrice());
        ticketRepository.save(newTicket);
        return new TicketDto(newTicket);
    }
}
