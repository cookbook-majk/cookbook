package cookbook.cookbookrecipeapplication.controllers;

import cookbook.cookbookrecipeapplication.models.RecentActivity;
import cookbook.cookbookrecipeapplication.models.Recipe;
import cookbook.cookbookrecipeapplication.models.Review;
import cookbook.cookbookrecipeapplication.models.User;
import cookbook.cookbookrecipeapplication.repositories.RecentActivityRepository;
import cookbook.cookbookrecipeapplication.repositories.ReviewRepository;
import cookbook.cookbookrecipeapplication.services.RecipeDaoService;
import cookbook.cookbookrecipeapplication.services.UserDaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
public class ReviewController {
    private RecipeDaoService recipeDao;
    private UserDaoService userDao;
    private RecentActivityRepository recentActivityDao;

    public ReviewController(RecipeDaoService recipeDao, UserDaoService userDao, RecentActivityRepository recentActivityDao) {
        this.recipeDao = recipeDao;
        this.userDao = userDao;
        this.recentActivityDao = recentActivityDao;
    }

    // Delete Review
    @GetMapping("/review/delete/{id}")
    public String deleteReview(@PathVariable long id) {
        long recipeId = recipeDao.getReviewById(id).getRecipe_id().getId();
        recipeDao.deleteRecentActivity(recentActivityDao.getRecentReviewActivityByUserIdAndRecipe(id));
        recipeDao.deleteReview(recipeDao.getReviewById(id));
        return "redirect:/recipe/" + recipeId;
    }

}

