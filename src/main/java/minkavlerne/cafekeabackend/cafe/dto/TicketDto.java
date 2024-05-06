package minkavlerne.cafekeabackend.cafe.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import minkavlerne.cafekeabackend.cafe.entity.Ticket;

@NoArgsConstructor
@Getter
@Setter
public class TicketDto {
    private int id;
    private int price;
    private String name;

    public TicketDto(Ticket ticket) {
        this.id = ticket.getId();
        this.price = ticket.getPrice();
        this.name = ticket.getName();
    }
}
