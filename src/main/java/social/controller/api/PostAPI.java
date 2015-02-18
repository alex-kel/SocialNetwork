package social.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import social.entity.Like;
import social.entity.Post;
import social.entity.User;
import social.repository.LikeRepository;
import social.repository.PostRepository;
import social.service.PostService;
import social.service.UserService;

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

    @RequestMapping(value = "post/changeLikeState", method = RequestMethod.POST)
    private @ResponseBody Object changeLikeState(@RequestParam Long id) {
        User sessionUser = userService.getUserById(1L);
        Post post = postService.getPostById(id);
        Set<Like> allLikes = post.getLikes();
        for (Like like : allLikes) {
            if (like.getOwner().equals(sessionUser)) {
                allLikes.remove(like);
                likeRepository.delete(like.getId());
                return "ok";
            }
        }
        Like like = new Like();
        like.setOwner(sessionUser);
        like.setPost(post);
        likeRepository.saveAndFlush(like);
        allLikes.add(like);
        return "ok";
    }
}
