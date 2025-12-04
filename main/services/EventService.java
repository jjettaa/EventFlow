package services;

import models.Event;
import repositories.EventRepository;
import java.util.List;

public class EventService {

    private EventRepository eventRepository = EventRepository.getInstance();

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Event getEventById(int id) {
        return eventRepository.findById(id);
    }

    public boolean createEvent(Event event) {
        if (event == null) return false;
        eventRepository.save(event);
        return true;
    }

    public boolean cancelEvent(int id) {
        return eventRepository.deleteById(id);
    }
}
