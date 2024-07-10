package alexburtyn.com.trainss.station.controllers;

import alexburtyn.com.trainss.station.models.Schedule;
import alexburtyn.com.trainss.station.models.Ticket;
import alexburtyn.com.trainss.station.models.TicketType;
import alexburtyn.com.trainss.station.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController()
@RequestMapping("/tickets")
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @GetMapping
    public List<Ticket> getAllTickets(){
        return ticketService.getAllTickets();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTicketById(@PathVariable Integer id){
        Ticket existingTicket = ticketService.getTicketById(id);
        if (existingTicket == null) {
            return new ResponseEntity<String>("Ticket with id: " + id + " not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Ticket>(existingTicket, HttpStatus.OK);
    }

    @PostMapping
    public Ticket createTicket(@RequestBody Ticket ticket){
        Date expiryDate = calculateDateOfExpiry(ticket.getDateOfPurchase(), ticket.getTicketType());
        ticket.setDateOfExpiry(expiryDate);
        return ticketService.saveTicket(ticket);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTicket(@PathVariable Integer id, @RequestBody Ticket ticket){
        Ticket existingTicket = ticketService.getTicketById(id);
        if (existingTicket == null) {
            return new ResponseEntity<String>("Ticket with id: " + id + " not found", HttpStatus.NOT_FOUND);
        }
        ticket.setId(id);
        Date expiryDate = calculateDateOfExpiry(ticket.getDateOfPurchase(), ticket.getTicketType());
        ticket.setDateOfExpiry(expiryDate);
        ticketService.saveTicket(ticket);
        return new ResponseEntity<Ticket>(ticket, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTicket(@PathVariable Integer id){
        Ticket ticket = ticketService.getTicketById(id);
        if (ticket == null) {
            return new ResponseEntity<String>("Ticket with id: " + id + " not found", HttpStatus.NOT_FOUND);
        }
        ticketService.deleteTicket(id);
        return new ResponseEntity<String>("Ticket with id: " + id + " was deleted", HttpStatus.OK);
    }

    private Date calculateDateOfExpiry(Date dateofPurchase, TicketType ticketType){
        Date dateOfExpiry = new Date();
        switch(ticketType){
            case HOUR:
                dateOfExpiry.setTime(dateofPurchase.getTime() + 60 * 60 * 1000);
                break;
            case DAY:
                dateOfExpiry.setTime(dateofPurchase.getTime() + 24 * 60 *60  * 1000);
                break;
            case WEEK:
                dateOfExpiry.setTime(dateofPurchase.getTime() + 7 * 24 * 60 *60  * 1000);
                break;
            case MONTH:
                dateOfExpiry.setTime(dateofPurchase.getTime() + 30 * 24 * 60 *60  * 1000);
                break;
        }
        return dateOfExpiry;
    }
}
