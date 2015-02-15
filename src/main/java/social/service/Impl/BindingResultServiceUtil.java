package social.service.Impl;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import social.service.BindingResultService;

import java.util.HashMap;

/**
 * Created by Alexander on 15.02.2015.
 */
@Service
public class BindingResultServiceUtil  implements BindingResultService{

    @Override
    public HashMap<String, Object> returnJson(BindingResult bindingResult) {
        HashMap<String, Object> returnJSON = new HashMap<>();
        returnJSON.put("errors", getErrors(bindingResult));
        return returnJSON;
    }

    private HashMap<String, String> getErrors(BindingResult bindingResult) {
        HashMap<String, String> errors = new HashMap<>();
        for (FieldError error : bindingResult.getFieldErrors()){
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return errors;
    }
}
