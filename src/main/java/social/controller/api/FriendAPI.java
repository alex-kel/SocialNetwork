package social.controller.api;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import social.entity.Followers;
import social.entity.User;
import social.repository.FollowersRepository;
import social.service.UserService;
import social.service.gson.GsonService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexander on 20.02.2015.
 */

@Controller
public class FriendAPI {

    @Autowired
    private UserService userService;

    @Autowired
    private FollowersRepository followersRepository;

    @Autowired
    private GsonService gsonService;

    @RequestMapping(value = "isFollowed", method = RequestMethod.POST)
    public @ResponseBody Object isFollowed(@RequestParam Long id) {
        User follower = userService.getCurrentSessionUser();
        User target = userService.getUserById(id);
        Followers followers = followersRepository.isFollowed(follower, target);
        if (followers != null) {
            return true;
        }
        return false;
    }

    @RequestMapping(value = "follow", method = RequestMethod.POST)
    public void follow(@RequestParam Long id) {
        User follower = userService.getCurrentSessionUser();
        User target = userService.getUserById(id);
        Followers followers = new Followers();
        followers.setFollower(follower);
        followers.setTarget(target);
        followersRepository.saveAndFlush(followers);
    }

    @RequestMapping(value = "unfollow", method = RequestMethod.POST)
    public void unfollow(@RequestParam Long id) {
        User follower = userService.getCurrentSessionUser();
        User target = userService.getUserById(id);
        Followers followers = followersRepository.getRow(follower, target);
        followersRepository.delete(followers.getId());
    }


    @RequestMapping(value = "getFollowers", method = RequestMethod.GET)
    public
    @ResponseBody
    Object getFollowers() {
        User user = userService.getCurrentSessionUser();
        Gson gson = gsonService.standardBuilder();
        List<Followers> subs = followersRepository.getFollowers(user);
        List<User> followers = new ArrayList<>();
        for (Followers f : subs) {
            followers.add(f.getFollower());
        }
        HttpHeaders h = new HttpHeaders();
        h.add("Content-type", "text/html;charset=UTF-8");
        return new ResponseEntity<String>(gson.toJson(followers), h, HttpStatus.OK);
    }

    @RequestMapping(value = "getFollowings", method = RequestMethod.GET)
    public
    @ResponseBody
    Object getFollowings() {
        User user = userService.getCurrentSessionUser();
        Gson gson = gsonService.standardBuilder();
        List<Followers> subs = followersRepository.getFollowing(user);
        List<User> following = new ArrayList<>();
        for (Followers f : subs) {
            following.add(f.getTarget());
        }
        HttpHeaders h = new HttpHeaders();
        h.add("Content-type", "text/html;charset=UTF-8");
        return new ResponseEntity<String>(gson.toJson(following), h, HttpStatus.OK);
    }


    @RequestMapping(value = "getAllUsers", method = RequestMethod.GET)
    public
    @ResponseBody
    Object getAllUsers() {
        List<User> users = userService.getAll();
        Gson gson = gsonService.standardBuilder();
        HttpHeaders h = new HttpHeaders();
        h.add("Content-type", "text/html;charset=UTF-8");
        return new ResponseEntity<String>(gson.toJson(users), h, HttpStatus.OK);
    }

    @RequestMapping(value = "getFriends", method = RequestMethod.GET)
    public
    @ResponseBody
    Object getFriends() {
        User user = userService.getCurrentSessionUser();
        Gson gson = gsonService.standardBuilder();
        List<Followers> followersList = followersRepository.getFriends();
        List<User> friends = new ArrayList<>();
        for (Followers followers : followersList) {
            if (followers.getFollower().getId() == user.getId()) {
                friends.add(followers.getTarget());
            }
        }
        HttpHeaders h = new HttpHeaders();
        h.add("Content-type", "text/html;charset=UTF-8");
        return new ResponseEntity<String>(gson.toJson(friends), h, HttpStatus.OK);
    }

}
