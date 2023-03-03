package cookbook.cookbookrecipeapplication.models;


import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "recent_activity")
public class RecentActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private int activity_type;

    @Column
    private Date createdAt;

    @ManyToOne
    @JoinColumn (name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn (name = "recipe_id")
    private Recipe recipe;


    public RecentActivity() {
    }

    public RecentActivity(int activity_type, Date createdAt, User user, Recipe recipe) {
        this.activity_type = activity_type;
        this.createdAt = createdAt;
        this.user = user;
        this.recipe = recipe;
    }

    public int getActivity_type() {
        return activity_type;
    }

    public void setActivity_type(int activity_type) {
        this.activity_type = activity_type;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
