package social.service.gson;

import com.google.gson.Gson;

/**
 * Created by Alexander on 15.02.2015.
 */
public interface GsonService {

    public Gson standardBuilder();

    public String error(String errorMessage);

    public String success(String successMessage);
}
