package social.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.hibernate.annotations.GenericGenerator;
import social.controller.forms.UserRegistrationForm;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Alexander on 12.02.2015.
 */
@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id", length = 6, nullable = false)
    @Expose
    private long id;

    @Column
    private String login;

    @Column
    private String password;

    @Expose
    @SerializedName("details")
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    private UserProfile userProfile;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "author", fetch = FetchType.EAGER)
    private Set<Post> authoredPosts = new HashSet<Post>(0);

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner", fetch = FetchType.EAGER)
    private Set<Post> wallPosts = new HashSet<Post>(0);

//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinColumn(name = "id")
//    private Set<User> followers;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public User() {

    }

    public User(UserRegistrationForm user) {
        this.password = user.getPassword();
        this.login = user.getLogin();
        this.userProfile = new UserProfile();
        this.userProfile.setUser(this);
        this.userProfile.setFullName(user.getFullName());
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }


    public Set<Post> getAuthoredPosts() {
        return authoredPosts;
    }

    public void setAuthoredPosts(Set<Post> authoredPosts) {
        this.authoredPosts = authoredPosts;
    }

    public Set<Post> getWallPosts() {
        return wallPosts;
    }

    public void setWallPosts(Set<Post> wallPosts) {
        this.wallPosts = wallPosts;
    }

//    public Set<User> getFollowers() {
//        return followers;
//    }
//
//    public void setFollowers(Set<User> followers) {
//        this.followers = followers;
//    }

    @Override
    public boolean equals(Object obj) {
        return this.getId() == ((User) obj).getId();
    }
}
