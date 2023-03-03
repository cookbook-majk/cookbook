package cookbook.cookbookrecipeapplication.models;


import jakarta.persistence.*;

@Entity
@Table( name = "followers")
public class Follower {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn (name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn (name = "follower_id")
    private User follower;

    public Follower() {
    }

    public Follower(User user, User follower) {
        this.user = user;
        this.follower = follower;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getFollower() {
        return follower;
    }

    public void setFollower(User follower) {
        this.follower = follower;
    }
}
