package cookbook.cookbookrecipeapplication.controllers;

import cookbook.cookbookrecipeapplication.PropertiesReader;
import cookbook.cookbookrecipeapplication.models.*;
import cookbook.cookbookrecipeapplication.services.ChapterDaoService;
import cookbook.cookbookrecipeapplication.services.RecipeDaoService;
import cookbook.cookbookrecipeapplication.services.UserDaoService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Security;
import java.util.*;

@Controller
public class RecipeController {
    private UserDaoService userDao;
    private RecipeDaoService recipeDao;
    private ChapterDaoService chapterDao;

    public RecipeController(UserDaoService userDao, RecipeDaoService recipeDao, ChapterDaoService chapterDao) {
        this.userDao = userDao;
        this.recipeDao = recipeDao;
        this.chapterDao = chapterDao;
    }

    // Draft a Recipe
    @GetMapping("/recipe/create")
    public String draftRecipe(Model model) {
        model.addAttribute("ingredients", new IngredientList());
        model.addAttribute("instructions", new InstructionList());
        model.addAttribute("filestack", PropertiesReader.getProperty("FILESTACK_API_KEY"));
        return "/create";
    }

    // Create a Recipe
    @PostMapping("/recipe/create")
    public String createRecipe(
            // Recipe Details
            @RequestParam(name = "title") String title,
            @RequestParam(name = "description") String summary,
            @RequestParam(name = "time") int readyInMinutes,
            @RequestParam(name = "servings") int servings,
            @RequestParam(name = "category") String category,
            @RequestParam(name = "imageURL") String imageURL,
            // Models
            Model model,
            @ModelAttribute IngredientList ingredients,
            @ModelAttribute InstructionList instructions
    ) {
        // Custom Recipe:
        CustomRecipe customRecipe = new CustomRecipe(
                servings,
                readyInMinutes,
                (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal(),
                summary
        );
        recipeDao.saveCustomRecipe(customRecipe);
        // Recipe:

        if (imageURL.equals("0")) {
            imageURL = "/images/default-recipe.jpg";
        }
        Recipe recipe = new Recipe(0,
                imageURL,
                ".jpg",
                new Date(),
                title,
                customRecipe
                );
        Set<DishType> categories = new HashSet<>();
        categories.add(recipeDao.getDishTypeById(Long.parseLong(category)));
        recipe.setDishTypes(categories);
        recipeDao.saveRecipe(recipe);

        // Saves ingredients & instructions to Recipe & CustomRecipe:
        for (Ingredient ingredient : ingredients.getIngredients()) {
            ingredient.setCustom_recipe(customRecipe);
            recipeDao.saveIngredient(ingredient);
        }
        for (Instruction instruction : instructions.getInstructions()) {
            instruction.setCustom_recipe(customRecipe);
            instruction.setOrder_num(instruction.getOrder_num());
            recipeDao.saveInstruction(instruction);
        }
        // Creates recent activity entry
        RecentActivity recentActivity = new RecentActivity(
                1,
                new Date(),
                (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal(),
                recipe
        );
        userDao.saveRecentActivity(recentActivity);
        return "redirect:/recipe/" + recipe.getId();
    }

    // View Recipe
    @GetMapping("/recipe/{id}")
    public String showRecipe(@PathVariable long id, Model model) {
        model.addAttribute("recipe", recipeDao.findRecipeById(id));
        model.addAttribute("saves", recipeDao.getNumberOfSavesByRecipeId(id));
        if (SecurityContextHolder.getContext().getAuthentication().getName() != null) {
            model.addAttribute("user", userDao.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
        }
        model.addAttribute("review", new Review());
        return "/recipe";
    }

    @PostMapping("/review")
    public String makeReview(
            @RequestParam(name = "title") String title,
            @RequestParam(name = "description") String comment,
            @RequestParam(name = "recipe") long recipe,
            @RequestParam(name = "user") long user,
            @RequestParam(name = "rating") int rating
    ) {

        Review review = new Review(
                new Date(),
                comment,
                rating,
                userDao.findUserById(user),
                recipeDao.findRecipeById(recipe),
                title
        );

        RecentActivity recentActivity = new RecentActivity(
                4,
                new Date(),
                (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal(),
                review
        );

        recipeDao.saveReview(review);
        userDao.saveRecentActivity(recentActivity);
        return "redirect:/recipe/" + recipe;
    }

    // View recipe from spoonacular
    @GetMapping("/recipe/sp/{spoonacularId}")
    public String showSpoonacularRecipe(@PathVariable long spoonacularId, Model model) throws IOException, InterruptedException {
        model.addAttribute("recipe", recipeDao.getRecipeAndCustomRecipeBySpoonacularId(spoonacularId));
        return "/recipe";
    }



//
//    // Edit a Recipe
//    @GetMapping("/posts/{id}/edit")
//    public String editPost(@PathVariable long id, Model model){
//        model.addAttribute("post", postService.getPostById(id));
//        return "posts/edit";
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


    // Draft an Edit
    @GetMapping("/recipe/{id}/edit")
    public String editDraft(Model model, @PathVariable long id) {
        model.addAttribute("recipe", recipeDao.findRecipeById(id).getCustom_recipe());
        model.addAttribute("recipeDao", recipeDao);
        model.addAttribute("filestack", PropertiesReader.getProperty("FILESTACK_API_KEY"));

        IngredientList ingredients = new IngredientList();
        List<Ingredient> ingredientsList = new ArrayList<>();
        ingredientsList.addAll(recipeDao.findRecipeById(id).getCustom_recipe().getIngredients());
        ingredients.setIngredients(ingredientsList);
        model.addAttribute("ingredients", ingredients);

        InstructionList instructions = new InstructionList();
        List<Instruction> instructionList = new ArrayList<>();
        instructionList.addAll(recipeDao.findRecipeById(id).getCustom_recipe().getInstructions());
        instructions.setInstructions(instructionList);
        model.addAttribute("instructions", instructions);

        return "/edit";
    }

    // Edit a Recipe
    @PostMapping("/recipe/edit")
    public String editRecipe(
            // Recipe Details
            @RequestParam(name = "id") long id,
            @RequestParam(name = "title") String title,
            @RequestParam(name = "description") String summary,
            @RequestParam(name = "time") int readyInMinutes,
            @RequestParam(name = "servings") int servings,
            @RequestParam(name = "category") String category,
            @RequestParam(name = "imageURL") String imageURL,
            // Models
            Model model,
            @ModelAttribute IngredientList ingredients,
            @ModelAttribute InstructionList instructions
    ) {
        // Recipe Objects:
        CustomRecipe customRecipe = recipeDao.findRecipeById(id).getCustom_recipe();
        Recipe recipe = recipeDao.findRecipeById(id);

        // Recipe Details:
        recipe.setTitle(title);
        customRecipe.setSummary(summary);
        customRecipe.setReadyInMinutes(readyInMinutes);
        customRecipe.setServings(servings);
        recipe.setImage(imageURL);

        Set<DishType> categories = new HashSet<>();
        categories.add(recipeDao.getDishTypeById(Long.parseLong(category)));
        recipe.setDishTypes(categories);

        // Ingredients & Instructions:
        List<Ingredient> ingredientList = new ArrayList<>();
        recipeDao.deleteIngredient(customRecipe.getIngredients());
        for (Ingredient ingredient : ingredients.getIngredients()) {
            ingredient.setCustom_recipe(customRecipe);
            ingredientList.add(ingredient);
        }
        customRecipe.setIngredients(ingredientList);

        List<Instruction> instructionList = new ArrayList<>();
        recipeDao.deleteInstruction(customRecipe.getInstructions());
        for (Instruction instruction : instructions.getInstructions()) {
            instruction.setCustom_recipe(customRecipe);
            instruction.setOrder_num(0);
            instructionList.add(instruction);
        }
        customRecipe.setInstructions(instructionList);

        recipe.setCustom_recipe(customRecipe);
        recipeDao.saveRecipe(recipe);
        recipeDao.saveCustomRecipe(customRecipe);

        return "redirect:/recipe/" + recipe.getId();
    }



}
