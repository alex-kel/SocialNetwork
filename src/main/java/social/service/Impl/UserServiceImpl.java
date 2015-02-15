package social.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import social.entity.User;
import org.springframework.stereotype.Service;
import social.repository.UserRepository;

import java.util.List;

/**
 * Created by Alexander on 12.02.2015.
 */
@Service
public class UserServiceImpl implements social.service.UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserByName(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public User addUser(User user) {
        User savedUser = userRepository.saveAndFlush(user);
        return savedUser;
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getCurrentSessionUser() {
        org.springframework.security.core.userdetails.User sessionUser = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContextHolderStrategy().getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByLogin(sessionUser.getUsername());
        return user;
    }
}
