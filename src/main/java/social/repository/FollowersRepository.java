package social.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import social.entity.Followers;
import social.entity.User;

import java.util.List;

/**
 * Created by Alexander on 20.02.2015.
 */
public interface FollowersRepository extends JpaRepository<Followers, Long> {

    @Query("select f from Followers f where f.follower = ?1 and f.target = ?2")
    Followers getRow(User follower, User target);

    @Query("select f from Followers f where f.target = ?1")
    List<Followers> getFollowers(User user);

    @Query("select f from Followers f where f.follower = ?1")
    List<Followers> getFollowing(User user);

    @Query("select f from Followers f where f.follower = ?1 and f.target = ?2")
    Followers isFollowed(User follower, User target);
}
