public interface UserRepository {
    User findById(int id);
    boolean save(User user);
    boolean delete(int id);
}
