package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.bootstrap.DataHolder;
import mk.finki.ukim.mk.lab.model.TicketOrder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TicketOrderRepository {

    public List<TicketOrder> findAll(){
        return DataHolder.ticketOrders;
    }
}
