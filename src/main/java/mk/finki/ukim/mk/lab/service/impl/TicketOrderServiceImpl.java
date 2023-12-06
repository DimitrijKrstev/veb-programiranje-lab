package mk.finki.ukim.mk.lab.service.impl;

import lombok.AllArgsConstructor;
import mk.finki.ukim.mk.lab.model.TicketOrder;
import mk.finki.ukim.mk.lab.service.MovieService;
import mk.finki.ukim.mk.lab.service.TicketOrderService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TicketOrderServiceImpl implements TicketOrderService {
    private final MovieService movieService;

    @Override
    public TicketOrder placeOrder(String movieTitle, String clientName, int numberOfTickets) {
        TicketOrder order = null; // new TicketOrder(movieTitle, clientName, address, numberOfTickets);
        return order;
    }
}
