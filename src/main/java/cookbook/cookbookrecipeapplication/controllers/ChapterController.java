package cookbook.cookbookrecipeapplication.controllers;

import cookbook.cookbookrecipeapplication.models.Chapter;
import cookbook.cookbookrecipeapplication.models.RecentActivity;
import cookbook.cookbookrecipeapplication.models.Recipe;
import cookbook.cookbookrecipeapplication.models.User;
import cookbook.cookbookrecipeapplication.services.ChapterDaoService;
import cookbook.cookbookrecipeapplication.services.RecipeDaoService;
import cookbook.cookbookrecipeapplication.services.UserDaoService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Date;
import java.util.Set;

@Controller
public class ChapterController {

    private UserDaoService userDao;
    private RecipeDaoService recipeDao;
    private ChapterDaoService chapterDao;

    public ChapterController(UserDaoService userDao, RecipeDaoService recipeDao, ChapterDaoService chapterDao) {
        this.userDao = userDao;
        this.recipeDao = recipeDao;
        this.chapterDao = chapterDao;
    }

    // Saving a Recipe to chapter
    @GetMapping("/chapter/save/{recipeId}")
    @ResponseBody
    public String saveRecipe(@PathVariable long recipeId) {
        //  Finds saved chapter by logged in user
        Chapter chapter = chapterDao.findSavedChapterByUser((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        //  Gets set of saved recipes from chapter
        Set<Recipe> userSavedRecipes = chapter.getSavedRecipes();
        //  Gets recipe from id in URI
        userSavedRecipes.add(recipeDao.findRecipeById(recipeId));
        //  Adds recipe to set of saved recipes then saves the chapter
        chapter.setSavedRecipes(userSavedRecipes);
        chapterDao.saveChapter(chapter);

        // Created recent activity for the addition
        RecentActivity recentActivity = new RecentActivity(
                2,
                new Date(),
                (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal(),
                recipeDao.findRecipeById(recipeId)
        );
        userDao.saveRecentActivity(recentActivity);
        return "Saved " + recipeDao.findRecipeById(recipeId).getTitle();
    }

    // Saving a Recipe from spoonacular to chapter
    @GetMapping("/chapter/save/sp/{spoonacularId}")
    @ResponseBody
    public String saveRecipeFromSpoonacular(@PathVariable long spoonacularId) throws IOException, InterruptedException {
        // Checks if recipe already exists in database
            // If it does not, it creates the recipe object and adds it to database then saves it to the users saved chapter
        if (recipeDao.findRecipeBySpoonacularId(spoonacularId) == null){
            // Creates the recipe object
            Recipe recipe = recipeDao.getRecipeSpoonacular(spoonacularId);
            recipeDao.saveRecipe(recipe);
            //  Finds saved chapter by logged in user
            Chapter chapter = chapterDao.findSavedChapterByUser((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
            //  Gets set of saved recipes from chapter
            Set<Recipe> userSavedRecipes = chapter.getSavedRecipes();
            //  Gets recipe from id in URI
            userSavedRecipes.add(recipe);
            //  Adds recipe to set of saved recipes then saves the chapter
            chapter.setSavedRecipes(userSavedRecipes);
            chapterDao.saveChapter(chapter);

            // Created recent activity for the addition
            RecentActivity recentActivity = new RecentActivity(
                    2,
                    new Date(),
                    (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal(),
                    recipe
            );
            userDao.saveRecentActivity(recentActivity);
            return "Saved " + recipe.getTitle();
        } else {
            saveRecipe(recipeDao.findRecipeBySpoonacularId(spoonacularId).getId());
            return "Saved " + recipeDao.findRecipeBySpoonacularId(spoonacularId).getTitle();
        }
    }

    @PostMapping("/chapter/unsave/{recipeId}")
    @ResponseBody
    public String unSaveRecipe(@PathVariable long recipeId) {
        //  Finds saved chapter by logged in user
        Chapter chapter = chapterDao.findSavedChapterByUser((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        chapter.getSavedRecipes().remove(recipeDao.findRecipeById(recipeId));
        chapterDao.saveChapter(chapter);
        return "Unsaved " + recipeDao.findRecipeById(recipeId).getTitle();
    }

}
