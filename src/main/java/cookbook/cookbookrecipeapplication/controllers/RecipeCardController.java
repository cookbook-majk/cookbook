package cookbook.cookbookrecipeapplication.controllers;

import cookbook.cookbookrecipeapplication.models.CustomRecipe;
import cookbook.cookbookrecipeapplication.models.Recipe;
import cookbook.cookbookrecipeapplication.models.RecipeCard;
import cookbook.cookbookrecipeapplication.models.User;
import cookbook.cookbookrecipeapplication.services.CustomRecipeDaoService;
import cookbook.cookbookrecipeapplication.services.RecipeDaoService;
import cookbook.cookbookrecipeapplication.services.UserDaoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class RecipeCardController {
    private final UserDaoService userDao;
    private final RecipeDaoService recipeDao;
    private final CustomRecipeDaoService customRecipeDao;

    public RecipeCardController(UserDaoService userDao, RecipeDaoService recipeDao, CustomRecipeDaoService customRecipeDao) {
        this.userDao = userDao;
        this.recipeDao = recipeDao;
        this.customRecipeDao = customRecipeDao;
    }

    @GetMapping("/recipe-cards")
    public String recipeCards(Model model) {
        List<CustomRecipe> customRecipes = customRecipeDao.findAll();
        List<RecipeCard> recipeCards = new ArrayList<>();

        for (CustomRecipe customRecipe : customRecipes) {
            User user = userDao.findUserById(customRecipe.getCreator_id().getId());
            Recipe recipe = recipeDao.findRecipeById(customRecipe.getRecipe().getId());
            RecipeCard recipeCard = new RecipeCard(user.getFirstName(), user.getLastName(),
                    recipe.getTitle(), recipe.getCreatedAt(), recipe.getImage());
            recipeCards.add(recipeCard);
        }
        model.addAttribute("recipeCards", recipeCards);
        return "recipe-cards";
    }

}
