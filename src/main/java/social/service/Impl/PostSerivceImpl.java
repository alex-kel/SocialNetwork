package social.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import social.entity.Post;
import social.repository.PostRepository;
import social.service.PostService;

/**
 * Created by kelale on 2/17/2015.
 */

@Service
public class PostSerivceImpl implements PostService {

    @Autowired
    PostRepository postRepository;

    @Override
    public Post addPost(Post post) {
        Post savedPost = postRepository.saveAndFlush(post);
        return savedPost;
    }
}
