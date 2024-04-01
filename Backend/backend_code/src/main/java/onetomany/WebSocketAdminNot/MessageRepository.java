package onetomany.WebSocketAdminNot;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<onetomany.WebSocketAdminNot.Message, Long>{

}
