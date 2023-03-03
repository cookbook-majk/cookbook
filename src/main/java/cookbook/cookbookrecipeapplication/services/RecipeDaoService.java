package cookbook.cookbookrecipeapplication.services;


import cookbook.cookbookrecipeapplication.models.CustomRecipe;
import cookbook.cookbookrecipeapplication.models.Recipe;
import cookbook.cookbookrecipeapplication.models.Review;
import cookbook.cookbookrecipeapplication.models.User;
import cookbook.cookbookrecipeapplication.repositories.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeDaoService {

    private final RecipeRepository recipeDao;
    private final CustomRecipeRepository customRecipeDao;
    private final IngredientRepository ingredientDao;
    private final InstructionRepository instructionDao;
    private final ReviewRepository reviewDao;


    public RecipeDaoService(RecipeRepository recipeRepository, CustomRecipeRepository customRecipeRepository, IngredientRepository ingredientRepository, InstructionRepository instructionRepository, ReviewRepository reviewRepository) {
        this.recipeDao = recipeRepository;
        this.customRecipeDao = customRecipeRepository;
        this.ingredientDao = ingredientRepository;
        this.instructionDao = instructionRepository;
        this.reviewDao = reviewRepository;
    }

    public Recipe findRecipeById(long id){
        return recipeDao.findById(id).get();
    }

    public List<CustomRecipe> findAllCustomRecipesByUser(User user){
        return customRecipeDao.findCustomRecipesByUser(user);
    }

    public List<Review> findAllReviewsByRecipeId(long id){
        return reviewDao.findAllByRecipe_id(id);
    }

}
