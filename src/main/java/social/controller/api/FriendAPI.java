package social.controller.api;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping(value = "getFriends", method = RequestMethod.GET)
    public
    @ResponseBody
    Object getFriends() {
        User user = userService.getCurrentSessionUser();
        Gson gson = gsonService.standardBuilder();
        return null;
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
        return gson.toJson(followers);
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
        return gson.toJson(following);
    }


}
