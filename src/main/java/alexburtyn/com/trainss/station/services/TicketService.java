package alexburtyn.com.trainss.station.services;


import alexburtyn.com.trainss.station.models.Ticket;
import alexburtyn.com.trainss.station.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public Ticket getTicketById(Integer id) {
        return ticketRepository.findById(id).orElse(null);
    }

    public Ticket saveTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    public void deleteTicket(Integer id) {
        ticketRepository.deleteById(id);
    }
}
