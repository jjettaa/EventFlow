package models;

public class Ticket {

    private int ticketId;
    private int eventId;
    private int ticketTypeId;   // which ticket type this ticket belongs to
    private double price;
    private boolean sold;

    public Ticket(int ticketId, int eventId, int ticketTypeId,
                  double price, boolean sold) {
        this.ticketId = ticketId;
        this.eventId = eventId;
        this.ticketTypeId = ticketTypeId;
        this.price = price;
        this.sold = sold;
    }

    public int getTicketId() { return ticketId; }
    public void setTicketId(int ticketId) { this.ticketId = ticketId; }

    public int getEventId() { return eventId; }
    public void setEventId(int eventId) { this.eventId = eventId; }

    public int getTicketTypeId() { return ticketTypeId; }
    public void setTicketTypeId(int ticketTypeId) { this.ticketTypeId = ticketTypeId; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public boolean isSold() { return sold; }
    public void setSold(boolean sold) { this.sold = sold; }

    private boolean cancelled;

public boolean isCancelled() { return cancelled; }
public void setCancelled(boolean cancelled) { this.cancelled = cancelled; }

}
