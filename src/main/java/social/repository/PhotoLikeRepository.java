package social.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import social.entity.Photo;
import social.entity.PhotoLike;
import social.entity.User;

import java.util.List;

/**
 * Created by kelale on 2/20/2015.
 */
public interface PhotoLikeRepository extends JpaRepository<PhotoLike, Long> {

    @Query("select l from PhotoLike l where l.owner = ?1 and l.photo = ?2")
    PhotoLike getLike(User user, Photo photo);

    @Query("select l from PhotoLike l where l.photo = ?1")
    List<PhotoLike> allLikes(Photo photo);
}
