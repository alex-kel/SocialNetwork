package social.service;

import social.entity.Post;

import java.util.List;
import java.util.Set;

/**
 * Created by kelale on 2/17/2015.
 */
public interface PostService {

    Post addPost(Post post);
    List<Post> getAllPostsByOwner(Long id);
}
