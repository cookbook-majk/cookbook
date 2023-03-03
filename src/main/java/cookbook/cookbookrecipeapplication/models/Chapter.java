package cookbook.cookbookrecipeapplication.models;

import jakarta.persistence.*;

import java.util.Set;


@Entity
@Table(name = "chapters")
public class Chapter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    private boolean featured;

    @ManyToMany
    @JoinTable(
            name = "recipe_chapter",
            joinColumns = @JoinColumn(name = "recipes"),
            inverseJoinColumns = @JoinColumn(name = "chapters"))
    Set<Recipe> savedRecipes;

    public Chapter() {
    }

    public Chapter(String name, String description, User user, boolean featured) {
        this.name = name;
        this.description = description;
        this.user = user;
        this.featured = featured;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isFeatured() {
        return featured;
    }

    public void setFeatured(boolean featured) {
        this.featured = featured;
    }
}
