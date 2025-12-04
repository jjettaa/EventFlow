package repositories;

import models.Tickettype;

import java.util.ArrayList;
import java.util.List;

public class TickettypeRepository {

    private static TickettypeRepository instance;

    private TickettypeRepository() {
    }

    public static TickettypeRepository getInstance() {
        if (instance == null) {
            instance = new TickettypeRepository();
        }
        return instance;
    }

    private List<Tickettype> types = new ArrayList<>();
    private int nextId = 1;

    public List<Tickettype> findAll() {
        return types;
    }

    public List<Tickettype> findByEventId(int eventId) {
        List<Tickettype> result = new ArrayList<>();
        for (Tickettype t : types) {
            if (t.getEventId() == eventId) {
                result.add(t);
            }
        }
        return result;
    }

    public Tickettype findById(int id) {
        for (Tickettype t : types) {
            if (t.getTicketTypeId() == id) {
                return t;
            }
        }
        return null;
    }

    public Tickettype save(Tickettype type) {
        type.setTicketTypeId(nextId++);
        types.add(type);
        return type;
    }
}
