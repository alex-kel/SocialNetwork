package social.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import social.entity.Like;

/**
 * Created by kelale on 2/16/2015.
 */
public interface LikeRepository extends JpaRepository<Like, Long> {
}
