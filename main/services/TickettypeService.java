package services;

import models.Tickettype;
import repositories.TickettypeRepository;

import java.util.List;

public class TickettypeService {

    private TickettypeRepository tickettypeRepository = TickettypeRepository.getInstance();

    public Tickettype createTickettype(int eventId, String name, double price, int quantity) {
        Tickettype type = new Tickettype(0, eventId, name, price, quantity);
        return tickettypeRepository.save(type);
    }

    public List<Tickettype> getAllTickettypes() {
        return tickettypeRepository.findAll();
    }

    public List<Tickettype> getTypesByEvent(int eventId) {
        return tickettypeRepository.findByEventId(eventId);
    }

    public Tickettype getById(int id) {
        return tickettypeRepository.findById(id);
    }
}
