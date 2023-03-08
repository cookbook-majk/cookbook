package cookbook.cookbookrecipeapplication.controllers;

import cookbook.cookbookrecipeapplication.PropertiesReader;
import cookbook.cookbookrecipeapplication.models.IngredientList;
import cookbook.cookbookrecipeapplication.models.InstructionList;
import cookbook.cookbookrecipeapplication.models.Recipe;
import cookbook.cookbookrecipeapplication.services.RecipeDaoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Properties;

@Controller
public class GeneralController {

    // Service Injection
    private final RecipeDaoService recipeService;
    public GeneralController(RecipeDaoService recipeService) {
        this.recipeService = recipeService;
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
    public String searchRecipes() { return "/search"; }

    // Get Results
    @GetMapping("/search/{param}")
    public String searchResults(@PathVariable String param, Model model) throws IOException, InterruptedException {
        model.addAttribute("searchResults", recipeService.getSearchResultsSpoonacular(param).getResults());
        return "/search";
    }

    // Draft a Recipe
    @GetMapping("/recipe/create")
    public String draftRecipe(Model model) {
        model.addAttribute("ingredients", new IngredientList());
        model.addAttribute("instructions", new InstructionList());
        return "/create";
    }

    // View Recipe
    @GetMapping("/recipe/{id}")
    public String showRecipe(@PathVariable long id) {
        recipeService.findRecipeById(id);
        return "/recipe";
    }
    @GetMapping("/recipe")
    public String oneRecipe() {
        return "/recipe";
    }

}
