package social.controller.api.forms;

/**
 * Created by Alexander on 17.02.2015.
 */
public class PostForm {

    String text;
    Long owner_id;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(Long owner_id) {
        this.owner_id = owner_id;
    }
}
