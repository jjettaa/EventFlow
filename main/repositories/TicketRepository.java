public interface TicketRepository {
    Ticket findById(int id);
    List<Ticket> findByEventId(int eventId);
    boolean save(Ticket ticket);
    boolean update(Ticket ticket);
}