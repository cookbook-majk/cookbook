package cookbook.cookbookrecipeapplication.models;

import jakarta.persistence.*;


import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "recipes")
public class Recipe implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = true)
    private long spoonacularId;

    @Column(nullable = false, length = 250)
    private String image;

    @Column(nullable = false, length = 9)
    private String image_type;

    @Column(nullable = false)
    private Date createdAt;

    @Column(nullable = false, length = 100)
    private String title;

    @OneToOne(cascade = CascadeType.ALL)
    private CustomRecipe custom_recipe;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    private List<Review> reviews;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    private List<RecentActivity> recentActivities;

    @ManyToMany(mappedBy = "savedRecipes")
    Set<Chapter> chapters;

    @ManyToMany
    @JoinTable(
            name = "recipe_types",
            joinColumns = @JoinColumn(name = "recipes"),
            inverseJoinColumns = @JoinColumn(name = "dish_types"))
    Set<DishType> dishTypes;

    public Recipe() {
    }

    public Recipe(long spoonacularId, String image, String image_type, Date createdAt, String title) {
        this.spoonacularId = spoonacularId;
        this.image = image;
        this.image_type = image_type;
        this.createdAt = createdAt;
        this.title = title;
    }

    public Recipe(long spoonacularId, String image, String image_type, Date createdAt, String title, CustomRecipe custom_recipe) {
        this.spoonacularId = spoonacularId;
        this.image = image;
        this.image_type = image_type;
        this.createdAt = createdAt;
        this.title = title;
        this.custom_recipe = custom_recipe;
    }

    public long getSpoonacularId() {
        return spoonacularId;
    }

    public void setSpoonacular_id(long spoonacularId) {
        this.spoonacularId = spoonacularId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage_type() {
        return image_type;
    }

    public void setImage_type(String image_type) {
        this.image_type = image_type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public Set<DishType> getDishTypes() {
        return dishTypes;
    }

    public void setDishTypes(Set<DishType> dishTypes) {
        this.dishTypes = dishTypes;
    }

    public long getId() { return id;}
    public void setId(long id) { this.id = id; }

    public CustomRecipe getCustom_recipe() {
        return custom_recipe;
    }

    public void setCustom_recipe(CustomRecipe custom_recipe) {
        this.custom_recipe = custom_recipe;
    }

    public Set<Chapter> getChapters() {
        return chapters;
    }

    public void setChapters(Set<Chapter> chapters) {
        this.chapters = chapters;
    }
}
