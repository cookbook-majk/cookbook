package cookbook.cookbookrecipeapplication.models;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    // Columns
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true, length = 25)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private Date userCreated;

    @Column
    private String userBio;

    @Column(nullable = false)
    private String profilePicture;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<CustomRecipe> custom_recipes;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Review> reviews;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private  List<Chapter> chapters;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private  List<RecentActivity> recentActivities;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private  List<Follower> userFollows;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "follower")
    private  List<Follower> following;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "target_user")
    private List<RecentActivity> targetUserRecentActivities;

    // Constructors
    public User(){}

    public User(String username, String password, String email, String firstName, String lastName, String profilePicture) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userCreated = new Date();
        this.profilePicture = profilePicture;
    }

    public User(User copy) {
        id = copy.id;
        email = copy.email;
        username = copy.username;
        password = copy.password;
    }

    // Getters and Setters
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public Date getUserCreated() { return userCreated; }
    public void setUserCreated(Date userCreated) { this.userCreated = userCreated; }

    public String getProfilePicture() { return profilePicture; }
    public void setProfilePicture(String profilePicture) { this.profilePicture = profilePicture; }

    public String getUserBio() {
        return userBio;
    }

    public void setUserBio(String userBio) {
        this.userBio = userBio;
    }

    public List<CustomRecipe> getCustom_recipes() {
        return custom_recipes;
    }

    public void setCustom_recipes(List<CustomRecipe> custom_recipes) {
        this.custom_recipes = custom_recipes;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<Chapter> getChapters() {
        return chapters;
    }

    public void setChapters(List<Chapter> chapters) {
        this.chapters = chapters;
    }

    public List<RecentActivity> getRecentActivities() {
        return recentActivities;
    }

    public void setRecentActivities(List<RecentActivity> recentActivities) {
        this.recentActivities = recentActivities;
    }

    public List<Follower> getUserFollows() {
        return userFollows;
    }

    public void setUserFollows(List<Follower> userFollows) {
        this.userFollows = userFollows;
    }

    public List<Follower> getFollowing() {
        return following;
    }

    public void setFollowing(List<Follower> following) {
        this.following = following;
    }
}

