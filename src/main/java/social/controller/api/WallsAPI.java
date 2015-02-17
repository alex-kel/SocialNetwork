package social.controller.api;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import social.controller.api.forms.PostForm;
import social.entity.Post;
import social.entity.User;
import social.repository.PostRepository;
import social.repository.UserRepository;
import social.service.PostService;
import social.service.UserService;
import social.service.gson.GsonService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Alexander on 17.02.2015.
 */

@Controller
public class WallsAPI {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GsonService gsonService;

    @Autowired
    private PostService postService;

    @RequestMapping(value = "wall/createPost", method = RequestMethod.POST)
    public @ResponseBody Object createPost(@RequestBody PostForm postForm,
                           BindingResult result,
                           HttpServletResponse response,
                           HttpServletRequest request) {
        User author = userService.getCurrentSessionUser();
        User owner = userService.getUserById(postForm.getOwner_id());
        Post post = new Post(postForm.getText(), author, owner);
        author.getAuthoredPosts().add(post);
        owner.getWallPosts().add(post);
        author.getAuthoredPosts().add(post);
        userRepository.save(author);
        Gson gson = gsonService.standardBuilder();
        response.setStatus(200);
        return gson.toJson(post);
    }

    @RequestMapping(value = "wall/getPosts", method = RequestMethod.GET)
    public
    @ResponseBody
    Object getAllPosts(@RequestParam Long id, HttpServletRequest request, HttpServletResponse response) {
        Gson gson = gsonService.standardBuilder();
        List<Post> posts = postService.getAllPostsByOwner(id);
        return gson.toJson(posts);
    }
}
