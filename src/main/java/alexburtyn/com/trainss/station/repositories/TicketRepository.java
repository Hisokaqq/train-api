package alexburtyn.com.trainss.station.repositories;

import alexburtyn.com.trainss.station.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {
}
