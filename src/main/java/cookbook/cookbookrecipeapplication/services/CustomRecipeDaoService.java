package cookbook.cookbookrecipeapplication.services;

import cookbook.cookbookrecipeapplication.models.CustomRecipe;
import cookbook.cookbookrecipeapplication.models.Recipe;
import cookbook.cookbookrecipeapplication.models.User;
import cookbook.cookbookrecipeapplication.repositories.CustomRecipeRepository;
import java.util.List;

public class CustomRecipeDaoService {
    private final CustomRecipeRepository customRecipeRepository;

    public CustomRecipeDaoService(CustomRecipeRepository customRecipeRepository) {
        this.customRecipeRepository = customRecipeRepository;
    }

    public List<CustomRecipe> findByUser(User user) {
        return customRecipeRepository.findByUser(user);
    }

    public CustomRecipe findByUserAndRecipe(User user, Recipe recipe) {
        return customRecipeRepository.findByUserAndRecipe(user, recipe);
    }

    public List<CustomRecipe> findAll() {
        return customRecipeRepository.findAll();
    }
}
