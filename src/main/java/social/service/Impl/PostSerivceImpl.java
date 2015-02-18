package social.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import social.entity.Post;
import social.repository.PostRepository;
import social.service.PostService;
import social.service.UserService;

import java.util.List;

/**
 * Created by kelale on 2/17/2015.
 */

@Service
public class PostSerivceImpl implements PostService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserService userService;

    @Override
    public Post addPost(Post post) {
        Post savedPost = postRepository.saveAndFlush(post);
        return savedPost;
    }

    @Override
    public List<Post> getAllPostsByOwner(Long id) {
        return postRepository.findAllByIdOwner(userService.getUserById(id));
    }

    @Override
    public Post getPostById(Long id) {
        return postRepository.findOne(id);
    }
}
