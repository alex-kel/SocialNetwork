package social.repository;

import org.jboss.logging.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import social.entity.User;

/**
 * Created by Alexander on 13.02.2015.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where u.login = ?1")
    User findByLogin(String login);
}
