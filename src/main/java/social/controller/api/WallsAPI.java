package social.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import social.controller.api.forms.PostForm;
import social.entity.Post;
import social.entity.User;
import social.repository.PostRepository;
import social.repository.UserRepository;
import social.service.PostService;
import social.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    private PostService postService;

    @RequestMapping(value = "wall/createPost", method = RequestMethod.POST)
    public void createPost(@RequestBody PostForm postForm,
                           BindingResult result,
                           HttpServletResponse response,
                           HttpServletRequest request) {
        User author = userService.getCurrentSessionUser();
        User owner = userService.getUserById(postForm.getOwner_id());
        Post post = new Post(postForm.getText(), author, owner);
        author.getAuthoredPosts().add(post);
        owner.getWallPosts().add(post);
        author.getAuthoredPosts().add(post);
        //postService.addPost(post); //there exception
        userRepository.save(author);
        response.setStatus(200);
    }
}
