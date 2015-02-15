package social.service;

import social.entity.User;

import java.util.List;

/**
 * Created by Alexander on 12.02.2015.
 */
public interface UserService {

    User getUserByName(String login);
    User addUser(User user);
    User getUserById(Long id);
    List<User> getAll();
    User getCurrentSessionUser();
}
