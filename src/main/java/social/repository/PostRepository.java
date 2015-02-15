package social.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import social.entity.Post;

/**
 * Created by Alexander on 15.02.2015.
 */
public interface PostRepository extends JpaRepository<Post, Long> {
}
