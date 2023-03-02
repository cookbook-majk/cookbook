package cookbook.cookbookrecipeapplication.models;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "custom_recipes")
public class Custom_recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, length = 250)
    private String image;

    @Column(nullable = false, length = 9)
    private String image_type;

    @Column(nullable = false)
    private int servings;

    @Column(nullable = false)
    private long readyInMinutes;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "custom_recipe")
    private List<Ingredient> ingredients;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "custom_recipe")
    private List<Instruction> instructions;

//    @OneToOne(cascade = CascadeType.ALL, mappedBy = "custom_recipe")
//    @JoinColumn(nullable = true)
//    private Recipe recipe;

//    @ManyToOne
//    @JoinColumn (name = "user_id")
//    private User creator_id;


    public Custom_recipe() {
    }

    public Custom_recipe(String title, String image, String image_type, int servings, long readyInMinutes, List<Ingredient> ingredients, List<Instruction> instructions) {
        this.title = title;
        this.image = image;
        this.image_type = image_type;
        this.servings = servings;
        this.readyInMinutes = readyInMinutes;
        this.ingredients = ingredients;
        this.instructions = instructions;
//        this.creator_id = creator_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public long getReadyInMinutes() {
        return readyInMinutes;
    }

    public void setReadyInMinutes(long readyInMinutes) {
        this.readyInMinutes = readyInMinutes;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<Instruction> getInstructions() {
        return instructions;
    }

    public void setInstructions(List<Instruction> instructions) {
        this.instructions = instructions;
    }

//    public User getCreator_id() {
//        return creator_id;
//    }

//    public void setCreator_id(User creator_id) {
//        this.creator_id = creator_id;
//    }
}
