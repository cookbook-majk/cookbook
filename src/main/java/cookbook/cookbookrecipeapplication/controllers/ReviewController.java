package cookbook.cookbookrecipeapplication.controllers;

import cookbook.cookbookrecipeapplication.models.RecentActivity;
import cookbook.cookbookrecipeapplication.models.Review;
import cookbook.cookbookrecipeapplication.models.User;
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

    public ReviewController(RecipeDaoService recipeDao, UserDaoService userDao) {
        this.recipeDao = recipeDao;
        this.userDao = userDao;
    }

    // Create a review
    @PostMapping("/review/{recipeId}")
    public void createReview(@ModelAttribute Review review, @PathVariable int recipeId) {
        review.setCreatedAt(new Date());
        RecentActivity recentActivity = new RecentActivity(
                4,
                new Date(),
                (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal(),
                review
        );
        userDao.saveRecentActivity(recentActivity);
        review.setRecipe_id(recipeDao.findRecipeById(recipeId));
        recipeDao.saveReview(review);
    }

    // Delete a review
    @DeleteMapping("/review/delete/{reviewId}")
    public void deleteReview(@PathVariable long reviewId) {
        recipeDao.deleteReview(recipeDao.getReviewById(reviewId));
    }
}

