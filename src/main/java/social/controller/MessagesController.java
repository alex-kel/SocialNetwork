package social.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Alexander on 22.02.2015.
 */
@Controller
@RequestMapping(value = "/messages")
public class MessagesController {

    @RequestMapping(method = RequestMethod.GET)
    public String messages() {
        return "messages";
    }
}
