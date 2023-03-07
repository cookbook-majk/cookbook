package cookbook.cookbookrecipeapplication.controllers;

import cookbook.cookbookrecipeapplication.models.*;
import cookbook.cookbookrecipeapplication.services.RecipeDaoService;
import cookbook.cookbookrecipeapplication.services.UserDaoService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    // Create a Recipe
    @PostMapping("/recipe/create")
    public String createRecipe(@RequestParam(name = "title") String title, @RequestParam(name = "summary") String summary, @RequestParam(name = "servings") int servings, @RequestParam(name = "readyInMinutes") int readyInMinutes, Model model) {
        CustomRecipe customRecipe = new CustomRecipe(servings, readyInMinutes, (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal(), summary);
        recipeDao.saveCustomRecipe(customRecipe);
        Recipe recipe = new Recipe(0, "image url", ".jpg", new Date(), title, customRecipe);
        recipeDao.saveRecipe(recipe);
        return "/recipe" + customRecipe.getId();
    }
//
//    // Edit a Recipe
//    @GetMapping("/posts/{id}/edit")
//    public String editPost(@PathVariable long id, Model model){
//        model.addAttribute("post", postService.getPostById(id));
//        return "posts/edit";
//    }


//    create recipe testing
//    @GetMapping("/createtest")
//    public String draftRecipe() {
//        CustomRecipe test = new CustomRecipe(
//                12, 32, (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal(), "one more recipe"
//        );
//
//        recipeDao.createCustomRecipe(test);
//
//        List<Ingredient> testIngre = new ArrayList<>();
//        testIngre.add(new Ingredient("banana", "banana", 2, test));
//        testIngre.add(new Ingredient("oats", "cup", 5, test));
//        testIngre.add(new Ingredient("vanilla extract", "ounce", 4.2, test));
//
//        List<Instruction> testInstr = new ArrayList<>();
//        testInstr.add(new Instruction(1, "peel bananas", test));
//        testInstr.add(new Instruction(2, "combine bananas and oats", test));
//        testInstr.add(new Instruction(3, "mix in vanilla", test));
//
//        for (Instruction instr : testInstr) {
//            recipeDao.saveInstruction(instr);
//        }
//        for (Ingredient ingr : testIngre) {
//            recipeDao.saveIngredient(ingr);
//        }
//        return "redirect:/feed";
//    }
    // RENDER RECIPES FOR PROFILE


//    @GetMapping("/recipe-cards")
//    public String getRecipeCards(Model model) {
//        List<RecipeCard> recipeCards = new ArrayList<>();
//        List<CustomRecipe> customRecipes = customRecipeService.getAllCustomRecipes();
//        for (CustomRecipe customRecipe : customRecipes) {
//            User user = userDao.getUserById(customRecipe.getUserId());
//            Recipe recipe = recipeDao.getRecipeById(customRecipe.getRecipeId());
//            RecipeCard recipeCard = new RecipeCard(user.getFirstName(), user.getLastName(),
//                    recipe.getTitle(), recipe.getCreatedAt(),
//                    recipe.getImage());
//            recipeCards.add(recipeCard);
//        }
//        model.addAttribute("recipeCards", recipeCards);
//        return "recipeCards";
//    }
}
