package mk.finki.ukim.mk.lab.service.impl;

import lombok.AllArgsConstructor;
import mk.finki.ukim.mk.lab.model.TicketOrder;
import mk.finki.ukim.mk.lab.repository.TicketOrderRepository;
import mk.finki.ukim.mk.lab.service.MovieService;
import mk.finki.ukim.mk.lab.service.TicketOrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TicketOrderServiceImpl implements TicketOrderService {
    private final MovieService movieService;
    private final TicketOrderRepository ticketOrderRepository;

    @Override
    public TicketOrder placeOrder(String movieTitle, String clientName, String address, int numberOfTickets) {
        TicketOrder order = new TicketOrder(movieTitle, clientName, address, numberOfTickets);
        ticketOrderRepository.findAll().add(order);
        return order;
    }

    @Override
    public List<TicketOrder> getTicketOrders() {
        return ticketOrderRepository.findAll();
    }
}
