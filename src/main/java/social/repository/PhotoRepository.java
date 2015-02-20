package social.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import social.entity.Photo;
import social.entity.User;

import java.util.List;

/**
 * Created by kelale on 2/20/2015.
 */
public interface PhotoRepository extends JpaRepository<Photo, Long> {

    @Query("select p from Photo p where p.owner = ?1")
    List<Photo> getAllPhotosByUser(User user);
}
