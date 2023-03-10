package cookbook.cookbookrecipeapplication.controllers;

import cookbook.cookbookrecipeapplication.PropertiesReader;
import cookbook.cookbookrecipeapplication.models.*;
import cookbook.cookbookrecipeapplication.services.RecipeDaoService;
import cookbook.cookbookrecipeapplication.services.UserDaoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Controller
public class GeneralController {

    // Service Injection
    private final RecipeDaoService recipeDao;
    private final UserDaoService userDao;
    public GeneralController(RecipeDaoService recipeDao, UserDaoService userDao) {
        this.recipeDao = recipeDao;
        this.userDao = userDao;
    }

    // Homepage
    @GetMapping("/home")
    public String showHome(){
        return "/home";
    }

    // About Us
    @GetMapping("/about")
    public String aboutUs(){
        return "/about";
    }

    // Browse & Discover
    @GetMapping("/browse")
    public String browseRecipes(){
        return "/browse";
    }

    // Search Recipes
    @GetMapping("/search")
    public String searchRecipes(@RequestParam("search") String search, Model model) throws IOException, InterruptedException {
        SearchResults searchResults = recipeDao.getSearchResultsSpoonacular(search);
        List<Recipe> recipes = searchResults.getResults();
        for (Recipe recipe : recipes){
            recipe.setId(recipe.getSpoonacularId());
            recipe.setReviews(new ArrayList<>());
        }
        searchResults.setResults(recipes);

        List<Recipe> cookbookRecipes = new ArrayList<>();

        List<Recipe> recipesByTitle = recipeDao.searchRecipesByTitle(search);
        cookbookRecipes.addAll(recipesByTitle);

        List<CustomRecipe> customRecipesBySummary = recipeDao.findCustomRecipesBySummary(search);
        for (CustomRecipe customRecipe : customRecipesBySummary){
            Recipe recipe = customRecipe.getRecipe();
            if (cookbookRecipes.contains(recipe)){

            } else {
                cookbookRecipes.add(recipe);
            }
        }

        model.addAttribute("cookbookRecipes", cookbookRecipes);

        model.addAttribute("user", userDao.findUserByUsername("Spoonacular"));
        model.addAttribute("searchResults", searchResults);
        model.addAttribute("searchTerm", search);
        return "/search";
    }

}
