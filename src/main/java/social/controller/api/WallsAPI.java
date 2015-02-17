package social.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import social.controller.api.forms.PostForm;
import social.entity.Post;
import social.entity.User;
import social.repository.PostRepository;
import social.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Alexander on 17.02.2015.
 */

@Controller
public class WallsAPI {

    @Autowired
    UserService userService;

    @Autowired
    PostRepository postRepository;

    @RequestMapping(value = "wall/createPost", method = RequestMethod.POST)
    public void createPost(@RequestBody PostForm postForm,
                           BindingResult result,
                           HttpServletResponse response,
                           HttpServletRequest request) {
        User owner = userService.getCurrentSessionUser();
        User author = userService.getUserById(postForm.getAuthor_user_id());
        Post post = new Post(postForm.getText(), author, owner);
        postRepository.save(post);
    }
}
