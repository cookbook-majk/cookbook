package cookbook.cookbookrecipeapplication.models;

import jakarta.persistence.*;


import java.util.Date;
import java.util.List;

@Entity
@Table(name = "recipes")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private long spoonacular_id;

    @Column(nullable = false)
    private Date createdAt;

    @Column(nullable = false, columnDefinition="TEXT")
    private String summary;

//    @OneToOne(cascade = CascadeType.ALL, mappedBy = "custom_recipe")
//    @JoinColumn(nullable = true)
//    private Custom_recipe custom_recipe;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    private List<Review> reviews;


    public Recipe() {
    }

    public Recipe(long spoonacular_id, Date createdAt, String summary) {
        this.spoonacular_id = spoonacular_id;
        this.createdAt = createdAt;
        this.summary = summary;
//        this.custom_recipe = custom_recipe;
    }

    public long getSpoonacular_id() {
        return spoonacular_id;
    }

    public void setSpoonacular_id(long spoonacular_id) {
        this.spoonacular_id = spoonacular_id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

//    public Custom_recipe getCustom_recipe() {
//        return custom_recipe;
//    }

//    public void setCustom_recipe(Custom_recipe custom_recipe) {
//        this.custom_recipe = custom_recipe;
//    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}
