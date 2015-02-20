package social.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import social.entity.Like;
import social.entity.Post;
import social.entity.User;
import social.repository.LikeRepository;
import social.repository.PostRepository;
import social.repository.UserRepository;
import social.service.PostService;
import social.service.UserService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Set;

/**
 * Created by Alexander on 18.02.2015.
 */
@Controller
public class PostAPI {

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private UserRepository userRepository;


    @RequestMapping(value = "post/changeLikeState", method = RequestMethod.POST)
    private
    @ResponseBody
    Object changeLikeState(@RequestParam Long id) {
        User sessionUser = userService.getCurrentSessionUser();
        Post post = postService.getPostById(id);
        Set<Like> allLikes = post.getLikes();
        for (Like like : allLikes) {
            if (like.getOwner().equals(sessionUser)) {
                likeRepository.delete(like);
                allLikes.remove(like);
                postRepository.save(post);
                return "unliked";
            }
        }
        Like like = new Like();
        like.setOwner(sessionUser);
        like.setPost(post);
        likeRepository.saveAndFlush(like);
        allLikes.add(like);
        return "liked";
    }

    @RequestMapping(value = "post/deletePost", method = RequestMethod.POST)
    @Transactional(readOnly = false, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    private
    @ResponseBody
    Object deletePost(@RequestParam Long id) {
        User sessionUser = null;
        try {
            sessionUser = userService.getCurrentSessionUser();
        } catch (Exception e) {
            return "No permissions";
        }
        Post post = postService.getPostById(id);
        if (post.getAuthor().getId() == sessionUser.getId() || post.getOwner().getId() == sessionUser.getId()) {
            post.setOwner(null);
            post.setAuthor(null);
            postRepository.save(post);
            return "deleted";
        }
        return "No permissions!";
    }

    private void removeFromAuthors(User user, Long postId) {
        for (Post post : user.getAuthoredPosts()) {
            if (post.getId() == postId) {
                user.getAuthoredPosts().remove(post);
                break;
            }
        }
    }

    private void removeFromWall(User user, Long postId) {
        for (Post post : user.getWallPosts()) {
            if (post.getId() == postId) {
                user.getWallPosts().remove(post);
                break;
            }
        }
    }
}
