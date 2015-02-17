package social.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import social.entity.Post;
import social.entity.User;

import java.util.List;
import java.util.Set;

/**
 * Created by Alexander on 15.02.2015.
 */
public interface PostRepository extends JpaRepository<Post, Long> {


    @Query("select p from Post p where p.owner = ?1")
    List<Post> findAllByIdOwner(User user);
}
