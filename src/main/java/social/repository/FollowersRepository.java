package social.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import social.entity.Followers;

/**
 * Created by Alexander on 20.02.2015.
 */
public interface FollowersRepository extends JpaRepository<Followers, Long> {


}
