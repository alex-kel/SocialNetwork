package social.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import social.controller.forms.ProfileEditForm;
import social.entity.User;
import social.entity.UserProfile;
import social.repository.UserProfileRepository;
import social.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

/**
 * Created by Alexander on 14.02.2015.
 */
@Controller
public class ProfileController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserProfileRepository userProfileRepository;

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public void editProfile(@RequestBody ProfileEditForm profileEditForm, BindingResult result,
                            HttpServletRequest request, HttpServletResponse response) throws ParseException {
        User user = userService.getCurrentSessionUser();
        UserProfile profile = user.getUserProfile();
        profile.setFullName(profileEditForm.getFullName());
        profile.setAbout(profileEditForm.getAbout());
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date date = null;
        try {
            date = format.parse(profileEditForm.getBirthDate());
        } catch (Exception e) {}
        profile.setBirthDate(date);
        profile.setEmail(profileEditForm.getEmail());
        profile.setCity(profileEditForm.getCity());
        profile.setPhoneNumber(profileEditForm.getPhoneNumber());
        userProfileRepository.save(profile);
        response.setStatus(200);
    }

    @RequestMapping(value = "/myprofile", method = RequestMethod.GET)
    public String redirectToSessionUserProfile() {
        Long id = userService.getCurrentSessionUser().getId();
        return "redirect:/profile/" + id;
    }

    @RequestMapping(value = "/profile/{sid}", method = RequestMethod.GET)
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
