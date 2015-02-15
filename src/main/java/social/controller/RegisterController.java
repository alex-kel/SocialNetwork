package social.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import social.entity.User;
import social.forms.UserRegistrationForm;
import social.service.UserDetailsServiceImpl;
import social.service.UserService;
import social.service.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;


/**
 * Created by Alexander on 13.02.2015.
 */
@Controller
@RequestMapping()
public class RegisterController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;


    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String showRegisterPage(Model model) {
        return "register";
    }


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public @ResponseBody Object addUser(@Valid UserRegistrationForm user,
                          BindingResult result,
                          HttpServletRequest request,
                          HttpServletResponse response) {
        isExist(user, result);
        isPasswordsMatch(user, result);
        if (result.hasErrors()) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            HashMap<String, Object> returnJSON = new HashMap<>();
            returnJSON.put("errors", getErrors(result));
            return gson.toJson(returnJSON);
        } else {
            userService.addUser(new User(user));
            return "redirect:/login";
        }

    }

    private void isExist(UserRegistrationForm form, Errors errors) {
        if (userService.getUserByName(form.getLogin()) != null) {
            errors.rejectValue("login", "Already exists");
        }
    }

    private void isPasswordsMatch(UserRegistrationForm form, Errors errors) {
        if (!form.getConfirmPassword().equals(form.getPassword())) {
            errors.rejectValue("password", "Password doesn`t match!");
        }
    }

    private HashMap<String, String> getErrors(BindingResult bindingResult) {
        HashMap<String, String> errors = new HashMap<>();
        for (FieldError error : bindingResult.getFieldErrors()){
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return errors;
    }
}
