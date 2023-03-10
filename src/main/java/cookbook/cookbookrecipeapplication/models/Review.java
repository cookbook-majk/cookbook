package cookbook.cookbookrecipeapplication.models;


import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private Date createdAt;

    @Column(nullable = false, length = 300)
    private String comment;

    @Column(nullable = false, length = 50)
    private String title;

    @Column
    private Date editedAt;

    @Column
    private int rating;

    @ManyToOne
    @JoinColumn (name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn (name = "recipe_id")
    private Recipe recipe;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "review")
    private List<RecentActivity> recentActivities;

    public Review() {
    }


    public Review(Date createdAt, String comment, int rating, User user, Recipe recipe, String title) {
        this.createdAt = createdAt;
        this.comment = comment;
        this.rating = rating;
        this.user = user;
        this.recipe = recipe;
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getEditedAt() {
        return editedAt;
    }

    public void setEditedAt(Date editedAt) {
        this.editedAt = editedAt;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public User getCreator_id() {
        return user;
    }

    public void setCreator_id(User creator_id) {
        this.user = user;
    }

    public Recipe getRecipe_id() {
        return recipe;
    }

    public void setRecipe_id(Recipe recipe_id) {
        this.recipe = recipe_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
