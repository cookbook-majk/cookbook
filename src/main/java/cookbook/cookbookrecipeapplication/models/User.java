package cookbook.cookbookrecipeapplication.models;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "user")
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

    @Column(columnDefinition = "TEXT")
    private String userBio;
    @Column(nullable = false)
    private String profilePicture;



    // Constructors
    public User(){}

    public User(String username, String password, String email, String firstName, String lastName, Date userCreated, String profilePicture) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userCreated = userCreated;
        this.profilePicture = profilePicture;
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
}

