package social.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import social.entity.User;
import social.entity.UserProfile;
import social.service.UserService;

/**
 * Created by Alexander on 14.02.2015.
 */
@Controller
@RequestMapping(value = "/profile/{sid}", method = RequestMethod.GET)
public class ProfileController {

    @Autowired
    private UserService userService;

    @RequestMapping()
    public String showProfile(@PathVariable String sid, Model model) {
        Long id = Long.parseLong(sid);
        User user = userService.getUserById(id);
        String currentSessionLogin = "lol";
        Long sessionId = -1L;
        try {
            User currentUser = userService.getCurrentSessionUser();
            sessionId = currentUser.getId();
            currentSessionLogin = currentUser.getLogin();
        } catch (Exception e) {
        }

        if (user == null) {
            return "redirect:/";
        } else {
            UserProfile userProfile = user.getUserProfile();
            model.addAttribute("fullName", userProfile.getFullName());
            model.addAttribute("phoneNumber", userProfile.getPhoneNumber());
            model.addAttribute("email", userProfile.getEmail());
            model.addAttribute("city", userProfile.getCity());
            model.addAttribute("about", userProfile.getAbout());
            model.addAttribute("birthDate", userProfile.getBirthDate());
            model.addAttribute("editable", currentSessionLogin.equals(user.getLogin()) );
            model.addAttribute("id", id);
            model.addAttribute("sessionId", sessionId);
            return "profile";
        }
    }
}
