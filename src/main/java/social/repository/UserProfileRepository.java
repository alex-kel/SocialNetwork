package social.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import social.entity.UserProfile;

/**
 * Created by Alexander on 13.02.2015.
 */
public interface UserProfileRepository extends JpaRepository<UserProfile, Long>{
}
