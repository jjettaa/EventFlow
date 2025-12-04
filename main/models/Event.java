package models;

import java.time.LocalDate;

public class Event {

    private int eventId;
    private String title;
    private String description;
    private String location;
    private LocalDate date;
    private int organizerId; 

    public Event() {}

    public Event(int eventId, String title, String description, String location,
                 LocalDate date, int organizerId) {
        this.eventId = eventId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.date = date;
        this.organizerId = organizerId;
    }

    public int getEventId() { return eventId; }
    public void setEventId(int eventId) { this.eventId = eventId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public int getOrganizerId() { return organizerId; }
    public void setOrganizerId(int organizerId) { this.organizerId = organizerId; }

    private boolean cancelled;

public boolean isCancelled() { return cancelled; }
public void setCancelled(boolean cancelled) { this.cancelled = cancelled; }

}
