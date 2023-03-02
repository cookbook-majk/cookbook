package cookbook.cookbookrecipeapplication.models;

import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "ingredients")
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 250)
    private String name;

    @Column(nullable = false, length = 20)
    private String unit;

    @Column(nullable = false)
    private double amount;

    @ManyToOne
    @JoinColumn (name = "custom_recipe_id")
    private Custom_recipe custom_recipe;


    public Ingredient() {
    }

    public Ingredient(String name, String unit, double amount, Custom_recipe custom_recipe) {
        this.name = name;
        this.unit = unit;
        this.amount = amount;
        this.custom_recipe = custom_recipe;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Custom_recipe getCustom_recipe() {
        return custom_recipe;
    }

    public void setCustom_recipe(Custom_recipe custom_recipe) {
        this.custom_recipe = custom_recipe;
    }
}
