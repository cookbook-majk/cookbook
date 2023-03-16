package cookbook.cookbookrecipeapplication.controllers;

import cookbook.cookbookrecipeapplication.PropertiesReader;
import cookbook.cookbookrecipeapplication.models.*;
import cookbook.cookbookrecipeapplication.services.RecipeDaoService;
import cookbook.cookbookrecipeapplication.services.UserDaoService;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public String showHome(Model model){
        model.addAttribute("recipeDao", recipeDao);
        model.addAttribute("userDao", userDao);
        model.addAttribute("popularRecipes", recipeDao.findMostSavedRecipes());
        model.addAttribute("trendingRecipes", recipeDao.findTrendingRecipes());
        if (SecurityContextHolder.getContext().getAuthentication().getName() != null) {
            model.addAttribute("loggedInUser", userDao.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
        }
        return "/home";
    }

    // About Us
    @GetMapping("/about")
    public String aboutUs(){
        return "/about";
    }

    // Browse & Discover
    @GetMapping("/browse")
    public String browseRecipes(Model model){
        if (SecurityContextHolder.getContext().getAuthentication().getName() != null) {
            model.addAttribute("loggedInUser", userDao.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
        }
        model.addAttribute("userDao", userDao);
        model.addAttribute("recipeDao", recipeDao);
        model.addAttribute("popularRecipes", recipeDao.findMostSavedRecipes());
        model.addAttribute("trendingRecipes", recipeDao.findTrendingRecipes());
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

        for (Recipe recipe : recipesByTitle) {
            if (recipe.getSpoonacularId() == 0) {
                cookbookRecipes.add(recipe);
            }
        }

        List<CustomRecipe> customRecipesBySummary = recipeDao.findCustomRecipesBySummary(search);
        for (CustomRecipe customRecipe : customRecipesBySummary){
            Recipe recipe = customRecipe.getRecipe();
            if (!cookbookRecipes.contains(recipe)){
                cookbookRecipes.add(recipe);
            }
        }

        if (SecurityContextHolder.getContext().getAuthentication().getName() != null) {
            User loggedInUser = userDao.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
            model.addAttribute("loggedInUser", loggedInUser);
        }

        model.addAttribute("cookbookRecipes", cookbookRecipes);
        model.addAttribute("recipeDao", recipeDao);
        model.addAttribute("userDao", userDao);
        model.addAttribute("user", userDao.findUserByUsername("Spoonacular"));
        model.addAttribute("searchResults", searchResults);
        model.addAttribute("searchTerm", search);
        return "/search";
    }

    // Search Recipes
    @GetMapping("/category/{categoryName}")
    public String searchRecipesCategory(@PathVariable String categoryName, Model model) throws IOException, InterruptedException {
        SearchResults searchResults = recipeDao.getCategorySearchResultsSpoonacular(categoryName);
        List<Recipe> recipes = searchResults.getResults();
        for (Recipe recipe : recipes){
            recipe.setId(recipe.getSpoonacularId());
            recipe.setReviews(new ArrayList<>());
        }
        searchResults.setResults(recipes);

        List<Recipe> cookbookRecipes = recipeDao.findRecipesByDishType((recipeDao.getDishTypeByName(categoryName)).getId());

        if (SecurityContextHolder.getContext().getAuthentication().getName() != null) {
            User loggedInUser = userDao.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
            model.addAttribute("loggedInUser", loggedInUser);
        }

        model.addAttribute("cookbookRecipes", cookbookRecipes);
        model.addAttribute("recipeDao", recipeDao);
        model.addAttribute("userDao", userDao);
        model.addAttribute("user", userDao.findUserByUsername("Spoonacular"));
        model.addAttribute("searchResults", searchResults);
        model.addAttribute("searchTerm", categoryName);
        return "/search";
    }

}
