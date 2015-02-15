package social.service.gson.Impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Service;
import social.service.gson.GsonService;

import java.util.HashMap;

/**
 * Created by Alexander on 15.02.2015.
 */
@Service
public class GsonServiceImpl implements GsonService {
    @Override
    public Gson standardBuilder() {
        return new GsonBuilder()
                .setPrettyPrinting()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
    }

    @Override
    public String error(String errorMessage) {
        HashMap<String, String>  error = new HashMap<>();
        error.put("error", errorMessage);
        return standardBuilder().toJson(error);
    }

    @Override
    public String success(String successMessage) {
        HashMap<String, String> success = new HashMap<>();
        success.put("error", successMessage);
        return standardBuilder().toJson(success);
    }
}
