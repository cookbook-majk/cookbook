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

//    // View a Recipe
//    @GetMapping("/posts/{id}")
//    public String viewPost(@PathVariable long id, Model model){
//        model.addAttribute("post", postService.getPostById(id));
//        return "posts/show";
//    }
//
//    // Draft a Recipe
//    @GetMapping("/posts/create")
//    public String draftPost(Model model){
//        model.addAttribute("post", new Post());
//        return "posts/create";
//    }
//
//    // Create a Recipe
//    @PostMapping("/posts/create")
//    public String createPost(@ModelAttribute Post post) {
//        postService.savePost(post);
//        emailService.sendTextEmail(post);
//        return "redirect:/posts/" + post.getId();
//    }
//
//    // Edit a Recipe
//    @GetMapping("/posts/{id}/edit")
//    public String editPost(@PathVariable long id, Model model){
//        model.addAttribute("post", postService.getPostById(id));
//        return "posts/edit";
//    }
//
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
