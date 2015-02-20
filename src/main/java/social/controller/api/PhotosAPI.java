package social.controller.api;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import social.entity.Photo;
import social.entity.User;
import social.repository.PhotoRepository;
import social.service.UserService;
import social.service.gson.GsonService;

import javax.servlet.ServletContext;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by kelale on 2/20/2015.
 */

@Controller
public class PhotosAPI {

    @Autowired
    ServletContext servletContext;

    @Autowired
    private UserService userService;

    @Autowired
    private PhotoRepository photoRepository;

    @Autowired
    private GsonService gsonService;

    @RequestMapping(value = "photos/getAll", method = RequestMethod.GET)
    public @ResponseBody Object getAllPhotos() {
        User user = userService.getCurrentSessionUser();
        List<Photo> allPhotos = photoRepository.getAllPhotosByUser(user);
        Gson gson = gsonService.standardBuilder();
        return gson.toJson(allPhotos);
    }

    @RequestMapping(value = "photos/upload", method = RequestMethod.POST)
    public String uploadPhoto(@RequestParam MultipartFile file) {
        User user = userService.getCurrentSessionUser();
        String name = file.getOriginalFilename();
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                String webappRoot = servletContext.getRealPath("/");
                String relativeFolder = File.separator + "resources" + File.separator
                        + "photos" + File.separator + userService.getCurrentSessionUser().getId() + File.separator;
                if (!Files.exists(Paths.get(webappRoot + relativeFolder)))
                    Files.createDirectories(Paths.get(webappRoot + relativeFolder));
                String afterRoot = relativeFolder + getFileName(file);
                String filename = webappRoot + afterRoot;
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File(filename)));
                stream.write(bytes);
                stream.close();
                String temp = "C:\\Development\\SocialNetwork\\src\\main\\webapp";
                String forCopy =  temp + afterRoot;
                if (!Files.exists(Paths.get(temp + relativeFolder)))
                    Files.createDirectories(Paths.get(temp + relativeFolder));
                BufferedOutputStream streamCopy =
                        new BufferedOutputStream(new FileOutputStream(new File(forCopy)));
                streamCopy.write(bytes);
                streamCopy.close();
                String relativeUrl = filename.substring((webappRoot).length());
                Photo photo = new Photo();
                photo.setRef(relativeUrl);
                photo.setOwner(user);
                photoRepository.save(photo);
            } catch (Exception e) {
            }
        }
        return "redirect:/myprofile/";
    }

    private String getFileName(MultipartFile file) {
        String name = file.getOriginalFilename();
        String format = name.substring(name.lastIndexOf('.'));
        return (new java.util.Date()).getTime() + format;
    }
}
