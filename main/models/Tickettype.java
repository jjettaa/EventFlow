package models;

public class Tickettype {

    private int ticketTypeId;
    private int eventId;              // event this type belongs to
    private String name;              // e.g. "Standard", "VIP"
    private double price;
    private int quantityAvailable;

    public Tickettype() {
    }

    public Tickettype(int ticketTypeId, int eventId,
                      String name, double price, int quantityAvailable) {
        this.ticketTypeId = ticketTypeId;
        this.eventId = eventId;
        this.name = name;
        this.price = price;
        this.quantityAvailable = quantityAvailable;
    }

    public int getTicketTypeId() { return ticketTypeId; }
    public void setTicketTypeId(int ticketTypeId) { this.ticketTypeId = ticketTypeId; }

    public int getEventId() { return eventId; }
    public void setEventId(int eventId) { this.eventId = eventId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getQuantityAvailable() { return quantityAvailable; }
    public void setQuantityAvailable(int quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }
}
