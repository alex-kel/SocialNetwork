package social.controller.api.forms;

/**
 * Created by Alexander on 17.02.2015.
 */
public class PostForm {

    String text;
    Long author_user_id, owner_user_id;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getAuthor_user_id() {
        return author_user_id;
    }

    public void setAuthor_user_id(Long author_user_id) {
        this.author_user_id = author_user_id;
    }

    public Long getOwner_user_id() {
        return owner_user_id;
    }

    public void setOwner_user_id(Long owner_user_id) {
        this.owner_user_id = owner_user_id;
    }
}
