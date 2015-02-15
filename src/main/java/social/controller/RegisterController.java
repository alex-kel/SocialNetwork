package social.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import social.entity.User;
import social.controller.forms.UserRegistrationForm;
import social.service.BindingResultService;
import social.service.UserService;
import social.service.gson.GsonService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


/**
 * Created by Alexander on 13.02.2015.
 */
@Controller
@RequestMapping()
public class RegisterController {

    @Autowired
    private UserService userService;

    @Autowired
    private GsonService gsonService;

    @Autowired
    private BindingResultService bindingResultService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String showRegisterPage(Model model) {
        return "register";
    }


    @RequestMapping(value = "/register",  method = RequestMethod.POST)
    public @ResponseBody Object addUser(@RequestBody @Valid UserRegistrationForm user,
                          BindingResult result,
                          HttpServletRequest request,
                          HttpServletResponse response) {
        isExist(user, result);
        isPasswordsMatch(user, result);
        Gson gson = gsonService.standardBuilder();
        if (result.hasErrors()) {
            response.setStatus(HttpServletResponse.SC_NON_AUTHORITATIVE_INFORMATION);
            System.out.println(bindingResultService);
            return gson.toJson(bindingResultService.returnJson(result));
        } else {
            response.setStatus(HttpServletResponse.SC_CREATED);
            User newUser = userService.addUser(new User(user));
            return gson.toJson(newUser);
        }

    }

    private void isExist(UserRegistrationForm form, Errors errors) {
        if (userService.getUserByName(form.getLogin()) != null) {
            errors.rejectValue("login", "login", "This login already exists");
        }
    }

    private void isPasswordsMatch(UserRegistrationForm form, Errors errors) {
        if (!form.getConfirmPassword().equals(form.getPassword())) {
            errors.rejectValue("passwords", "password", "Passwords doesn`t match!");
        }
    }
}
