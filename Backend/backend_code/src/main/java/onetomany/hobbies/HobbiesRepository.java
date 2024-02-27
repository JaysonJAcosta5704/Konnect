package onetomany.hobbies;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface HobbiesRepository extends JpaRepository<Hobbies, Integer>{
    Hobbies findById(int id);
    void deleteById(int id);
}
