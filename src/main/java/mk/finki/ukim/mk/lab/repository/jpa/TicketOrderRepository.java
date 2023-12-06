package mk.finki.ukim.mk.lab.repository.jpa;

import mk.finki.ukim.mk.lab.model.TicketOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface TicketOrderRepository extends JpaRepository<TicketOrder, Long> {
    Optional<TicketOrder> findByUserUsername(String username);

    List<TicketOrder> findAllByUserUsername(String username);

    List<TicketOrder> findAllByOrderDateAfterAndOrderDateBefore(LocalDateTime after, LocalDateTime before);
}
