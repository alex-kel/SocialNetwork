package social.controller.api;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import social.entity.Message;
import social.entity.User;
import social.repository.MessageRepository;
import social.service.UserService;
import social.service.gson.GsonService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Alexander on 22.02.2015.
 */
@Controller
public class MessageAPI {

    @Autowired
    private UserService userService;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private GsonService gsonService;

    @RequestMapping(value = "sendMessage", method = RequestMethod.POST)
    public void sendMessage(@RequestParam Long id, String text, HttpServletResponse response) {
        User author = userService.getCurrentSessionUser();
        User receiver = userService.getUserById(id);
        Message message = new Message();
        message.setAuthor(author);
        message.setReceiver(receiver);
        message.setText(text);
        message.setIsRead(false);
        messageRepository.saveAndFlush(message);
        response.setStatus(200);
    }

    @RequestMapping(value = "getIncomingMessages", method = RequestMethod.GET)
    public
    @ResponseBody
    Object getIncomingMessages() {
        Gson gson = gsonService.standardBuilder();
        User user = userService.getCurrentSessionUser();
        List<Message> messages = messageRepository.getIncoming(user);
        return gson.toJson(messages);
    }

    @RequestMapping(value = "getOutcomingMessages", method = RequestMethod.GET)
    public
    @ResponseBody
    Object getOutomingMessages() {
        Gson gson = gsonService.standardBuilder();
        User user = userService.getCurrentSessionUser();
        List<Message> messages = messageRepository.getOutcoming(user);
        return gson.toJson(messages);
    }

    @RequestMapping(value = "getNewMessageCount", method = RequestMethod.GET)
    public
    @ResponseBody
    Object getNewMessageCount() {
        User user = userService.getCurrentSessionUser();
        return messageRepository.getNew(user).size();
    }
}
