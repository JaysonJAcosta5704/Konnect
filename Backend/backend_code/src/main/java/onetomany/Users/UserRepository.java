package onetomany.Users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
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
    List<User> findByUsernameContaining(String username);
    @Override
    void delete(User entity);
}
