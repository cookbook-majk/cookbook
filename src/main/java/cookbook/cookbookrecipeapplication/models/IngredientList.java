package cookbook.cookbookrecipeapplication.models;

import java.util.List;

public class IngredientList {

    public List<Ingredient> ingredients;

    public IngredientList() {
    }

    public IngredientList(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}
