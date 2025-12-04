package repositories;

import models.Event;
import java.util.ArrayList;
import java.util.List;

public class EventRepository {

    private static EventRepository instance;

    private EventRepository() {}

    public static EventRepository getInstance() {
        if (instance == null) instance = new EventRepository();
        return instance;
    }

    private List<Event> events = new ArrayList<>();
    private int nextId = 1;

    public List<Event> findAll() {
        return events;
    }

    public Event findById(int id) {
        for (Event e : events) {
            if (e.getEventId() == id) return e;
        }
        return null;
    }

 public Event save(Event event) {
    // Assign ID only if it's a new event
    if (event.getEventId() == 0) {
        event.setEventId(nextId++);
        events.add(event);
    }
    return event;
}


    public boolean deleteById(int id) {
        return events.removeIf(e -> e.getEventId() == id);
    }
}
