package social.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import social.entity.Message;
import social.entity.User;

import java.util.List;

/**
 * Created by Alexander on 22.02.2015.
 */
public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query("select m from Message m where m.receiver = ?1")
    List<Message> getIncoming(User user);

    @Query("select m from Message m where m.author = ?1")
    List<Message> getOutcoming(User user);

    @Query("select m from Message m where m.receiver = ?1 and m.isRead = false")
    List<Message> getNew(User user);

}
