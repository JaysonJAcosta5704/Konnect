package onetomany.Users;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Vivek Bengre
 *
 */

public interface UserRepository extends JpaRepository<User, Long> {
    User findById(int id);
    void deleteById(int id);
    void deleteUserById(int id);
    User findUserByUsername(String username);
    User findByEmailId(String emailId);
    User findByUsername(String username);
    List<User> findByUsernameContaining(String username);
    @Override
    void delete(User entity);
}
