package cookbook.cookbookrecipeapplication.services;


import cookbook.cookbookrecipeapplication.repositories.Custom_recipeRepository;
import cookbook.cookbookrecipeapplication.repositories.IngredientRepository;
import cookbook.cookbookrecipeapplication.repositories.InstructionRepository;
import cookbook.cookbookrecipeapplication.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

@Service
public class RecipeDaoService {

    private final RecipeRepository recipeRepository;
    private final Custom_recipeRepository custom_recipeRepository;
    private final IngredientRepository ingredientRepository;
    private final InstructionRepository instructionRepository;


    public RecipeDaoService(RecipeRepository recipeRepository, Custom_recipeRepository custom_recipeRepository, IngredientRepository ingredientRepository, InstructionRepository instructionRepository) {
        this.recipeRepository = recipeRepository;
        this.custom_recipeRepository = custom_recipeRepository;
        this.ingredientRepository = ingredientRepository;
        this.instructionRepository = instructionRepository;
    }


}
