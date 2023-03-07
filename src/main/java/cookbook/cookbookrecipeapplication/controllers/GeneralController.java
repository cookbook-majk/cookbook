package cookbook.cookbookrecipeapplication.controllers;

import cookbook.cookbookrecipeapplication.models.Recipe;
import cookbook.cookbookrecipeapplication.services.RecipeDaoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

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
        recipeService.getSearchResultsSpoonacular(param);
        return "/search";
    }

    // Draft a Recipe
    @GetMapping("/recipe/create")
    public String draftRecipe(Model model) {
        model.addAttribute("recipe", new Recipe());
        return "/create";
    }

    // Create a Recipe
//    @PostMapping("/register")
//    public String createUser(@ModelAttribute Recipe recipe) {
//        recipeService.
//        return "redirect:/login";
//    }

//    // Submit an Edit
//    @PostMapping("/posts/edit")
//    public String updatePost(@ModelAttribute Post post) {
//        postService.savePost(post);
//        return "redirect:/posts/" + post.getId();
//    }
//
//    // Delete a Recipe
//    @GetMapping("/posts/{id}/delete")
//    public String deletePost(@PathVariable long id) {
//        postService.deletePostById(id);
//        return "redirect:/posts";
//    }

}
