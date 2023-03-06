package cookbook.cookbookrecipeapplication.controllers;

import cookbook.cookbookrecipeapplication.models.Recipe;
import cookbook.cookbookrecipeapplication.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class RecipeController {

    @GetMapping("/recipe/create")
    public String draftRecipe(Model model) {
        model.addAttribute("recipe", new Recipe());
        return "/create-recipe";
    }
}
