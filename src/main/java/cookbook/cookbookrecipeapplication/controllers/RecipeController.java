package cookbook.cookbookrecipeapplication.controllers;

import cookbook.cookbookrecipeapplication.models.Recipe;
import cookbook.cookbookrecipeapplication.services.RecipeDaoService;
import cookbook.cookbookrecipeapplication.services.UserDaoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RecipeController {
    private UserDaoService userDao;
    private RecipeDaoService recipeDao;

    public RecipeController(UserDaoService userDao, RecipeDaoService recipeDao) {
        this.userDao = userDao;
        this.recipeDao = recipeDao;
    }

    //    // View a Recipe
//    @GetMapping("/posts/{id}")
//    public String viewPost(@PathVariable long id, Model model){
//        model.addAttribute("post", postService.getPostById(id));
//        return "posts/show";
//    }
//
//    // Draft a Recipe
//    @GetMapping("/recipe/create")
//    public String draftRecipe(){
//        return "/create-recipe";
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
}
