package mk.finki.ukim.mk.lab.service.impl;

import lombok.AllArgsConstructor;
import mk.finki.ukim.mk.lab.model.ShoppingCart;
import mk.finki.ukim.mk.lab.model.TicketOrder;
import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.repository.jpa.ShoppingCartRepository;
import mk.finki.ukim.mk.lab.repository.jpa.TicketOrderRepository;
import mk.finki.ukim.mk.lab.service.TicketOrderService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TicketOrderServiceImpl implements TicketOrderService {
    private final TicketOrderRepository ticketOrderRepository;
    private final ShoppingCartRepository shoppingCartRepository;

    @Override
    public TicketOrder placeOrder(String movieTitle, User user, int numberOfTickets) {
        TicketOrder order = TicketOrder.builder().movieTitle(movieTitle).user(user).numberOfTickets(numberOfTickets).build();
        ticketOrderRepository.save(order);
        ShoppingCart cart = shoppingCartRepository.findByUserId(user.getId()).orElse(null);
        if (cart != null) {
            cart.getTicketOrders().add(order);
            shoppingCartRepository.save(cart);
        }

        return order;
    }
}
