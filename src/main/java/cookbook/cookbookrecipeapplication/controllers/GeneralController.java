package cookbook.cookbookrecipeapplication.controllers;

import cookbook.cookbookrecipeapplication.PropertiesReader;
import cookbook.cookbookrecipeapplication.models.IngredientList;
import cookbook.cookbookrecipeapplication.models.InstructionList;
import cookbook.cookbookrecipeapplication.models.Recipe;
import cookbook.cookbookrecipeapplication.models.SearchResults;
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
    private final RecipeDaoService recipeService;
    private final UserDaoService userDao;
    public GeneralController(RecipeDaoService recipeService, UserDaoService userDao) {
        this.recipeService = recipeService;
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
        SearchResults searchResults = recipeService.getSearchResultsSpoonacular(search);
        List<Recipe> recipes = searchResults.getResults();
        for (Recipe recipe : recipes){
            recipe.setReviews(new ArrayList<>());
            recipe.setId(0);
        }
        searchResults.setResults(recipes);
        model.addAttribute("user", userDao.findUserByUsername("spoonacular"));
        model.addAttribute("searchResults", searchResults);
        model.addAttribute("searchTerm", search);
        return "/search";
    }

}
