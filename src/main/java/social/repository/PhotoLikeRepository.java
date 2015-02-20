package social.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import social.entity.PhotoLike;

/**
 * Created by kelale on 2/20/2015.
 */
public interface PhotoLikeRepository extends JpaRepository<PhotoLike, Long> {
}
