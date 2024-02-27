package onetomany.Matches;

import org.springframework.data.jpa.repository.JpaRepository;
public interface MatchesRepository  extends JpaRepository<Match, Long>{


    Match findById(int id);
}
