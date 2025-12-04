package repositories;

import models.Ticket;
import java.util.ArrayList;
import java.util.List;

public class TicketRepository {

    private static TicketRepository instance;

    private TicketRepository() {}

    public static TicketRepository getInstance() {
        if (instance == null) instance = new TicketRepository();
        return instance;
    }

    private List<Ticket> tickets = new ArrayList<>();
    private int nextId = 1;

    public List<Ticket> findAll() {
        return tickets;
    }

    public List<Ticket> findByEventId(int eventId) {
        List<Ticket> result = new ArrayList<>();
        for (Ticket t : tickets) {
            if (t.getEventId() == eventId) result.add(t);
        }
        return result;
    }

    public Ticket findById(int id) {
        for (Ticket t : tickets) {
            if (t.getTicketId() == id) return t;
        }
        return null;
    }

public Ticket save(Ticket ticket) {
    if (ticket.getTicketId() == 0) {
        ticket.setTicketId(nextId++);
        tickets.add(ticket);
    }
    return ticket;
}

}
