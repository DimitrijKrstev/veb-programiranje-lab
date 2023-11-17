package mk.finki.ukim.mk.lab.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TicketOrder {
    private String movieTitle;
    private String clientName;
    private String clientAddress;
    private Integer numberOfTickets;
}
