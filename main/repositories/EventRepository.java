public interface EventRepository {
    Event findById(int id);
    List<Event> findAll();
    boolean save(Event event);
    boolean delete(int id);
}