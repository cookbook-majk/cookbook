package cookbook.cookbookrecipeapplication.models;


import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "custom_recipes")
public class CustomRecipe implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;



    @Column(nullable = false, columnDefinition="TEXT")
    private String summary;

    @Column(nullable = false)
    private int servings;

    @Column(nullable = false)
    private long readyInMinutes;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "custom_recipe")
    private List<Ingredient> ingredients;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "custom_recipe")
    private List<Instruction> instructions;

    @OneToOne(mappedBy = "custom_recipe")
    private Recipe recipe;

    @ManyToOne
    @JoinColumn (name = "user_id")
    private User user;


    public CustomRecipe() {
    }

    public CustomRecipe(int servings, long readyInMinutes, List<Ingredient> ingredients, List<Instruction> instructions, User user, String summary) {
        this.servings = servings;
        this.readyInMinutes = readyInMinutes;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.user = user;
        this.summary = summary;
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



    public User getCreator_id() {
        return user;
    }

    public void setCreator_id(User user) {
        this.user = user;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
