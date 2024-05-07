package minkavlerne.cafekeabackend.cafe.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import minkavlerne.cafekeabackend.cafe.entity.CustomerTicket;

@NoArgsConstructor
@Getter
@Setter

public class CustomerTicketDto {
    private int id;
    private int ticketId;
    private int customerId;
    private int quantity;
    private String createdAt;

    public CustomerTicketDto(CustomerTicket customerTicket) {
        this.id = customerTicket.getId();
        this.ticketId = customerTicket.getTicket().getId();
        this.customerId = customerTicket.getCustomer().getId();
        this.quantity = customerTicket.getQuantity();
        this.createdAt = customerTicket.getCreatedAt().toString();
    }
}
