package social.entity;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import social.controller.api.forms.PostForm;
import social.service.UserService;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Alexander on 15.02.2015.
 */
@Entity
@Table(name = "Posts")
public class Post {


    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    @Column(name = "post_id")
    @Expose
    private long id;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    public Post(String text, User author, User owner) {
        this.text = text;
        this.author = author;
        this.owner = owner;
        this.date = new Date();
    }

    @Expose
    @ManyToOne
    private User author;

    @Expose
    private String text;

    @Expose
    private Date date;

    public Post() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

//    public Set<Like> getLikes() {
//        return likes;
//    }
//
//    public void setLikes(Set<Like> likes) {
//        this.likes = likes;
//    }
}
