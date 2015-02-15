package social.service;

import org.springframework.validation.BindingResult;

import java.util.HashMap;

/**
 * Created by Alexander on 15.02.2015.
 */
public interface BindingResultService {

    public HashMap<String, Object> returnJson(BindingResult bindingResult);
}
