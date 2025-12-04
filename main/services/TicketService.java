package services;

import models.Ticket;
import repositories.TicketRepository;
import java.util.List;

public class TicketService {

    private TicketRepository ticketRepository = TicketRepository.getInstance();

    public Ticket getById(int id) {
        return ticketRepository.findById(id);
    }

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public List<Ticket> getTicketsByEvent(int eventId) {
        return ticketRepository.findByEventId(eventId);
    }

    public boolean validateTicket(int ticketId) {
    Ticket ticket = ticketRepository.findById(ticketId);
    return ticket != null && !ticket.isSold() && !ticket.isCancelled();
}

    public boolean issueTicket(Ticket ticket) {
        if (ticket == null) return false;
        ticketRepository.save(ticket);
        return true;
    }
}
