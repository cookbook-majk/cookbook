package cookbook.cookbookrecipeapplication.services;


import cookbook.cookbookrecipeapplication.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

@Service
public class RecipeDaoService {

    private final RecipeRepository recipeRepository;


    public RecipeDaoService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }
}
