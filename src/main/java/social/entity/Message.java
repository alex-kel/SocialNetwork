package social.entity;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

/**
 * Created by Alexander on 22.02.2015.
 */
@Entity
@Table(name = "message")
public class Message {

    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    @Column(name = "id")
    @Expose
    private long id;

    @OneToOne
    @Expose
    private User author;

    @OneToOne
    @Expose
    private User receiver;

    @Expose
    private Boolean isRead;

    @Length(max = 10000)
    @Expose
    private String text;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public Boolean getIsRead() {
        return isRead;
    }

    public void setIsRead(Boolean isRead) {
        this.isRead = isRead;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
