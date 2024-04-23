package onetomany.Users;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Daniel Pnilla
 *
 */

public interface UserRepository extends JpaRepository<User, Long> {
    User findById(int id);

    User findUserByUsername(String username);
    User findByEmailId(String emailId);
    User findByUsername(String username);


}
