public class Event {
    private int eventId;
    private String title;
    private String description;
    private Date date;
    private String location;
    private int organizerId;

    public void addTicketType(TicketType ticketType) {}
    public boolean updateEvent() { return false; }
    public boolean cancelEvent() { return false; }
}
