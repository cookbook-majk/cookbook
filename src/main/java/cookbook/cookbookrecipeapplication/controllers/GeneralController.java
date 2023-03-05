package cookbook.cookbookrecipeapplication.controllers;

import cookbook.cookbookrecipeapplication.services.RecipeDaoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class GeneralController {

    // Service Injection
    private final RecipeDaoService recipeService;

    public GeneralController(RecipeDaoService recipeService) {
        this.recipeService = recipeService;
    }

    // Browse All Recipes
    @GetMapping("/home")
    public String showHome(){
        return "/home";
    }

    // Draft a Recipe
    @GetMapping("/recipe/create")
    public String draftRecipe() { return "/create"; }

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
