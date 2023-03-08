package cookbook.cookbookrecipeapplication.controllers;

import cookbook.cookbookrecipeapplication.models.RecentActivity;
import cookbook.cookbookrecipeapplication.models.Review;
import cookbook.cookbookrecipeapplication.models.User;
import cookbook.cookbookrecipeapplication.repositories.ReviewRepository;
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
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserDaoService userDao;


    // CREATE REVIEW
    @PostMapping("/reviews")
    public Review createReview(@Valid @RequestBody Review review) {
        review.setCreatedAt(new Date());
        RecentActivity recentActivity = new RecentActivity(
                4,
                new Date(),
                (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal(),
                review
        );
        userDao.saveRecentActivity(recentActivity);
        return reviewRepository.save(review);
    }
    // READ REVIEW
    @GetMapping("/reviews/{id}")
    public Review getReviewById(@PathVariable(value = "id") long reviewId){
        return reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ResourceNotFoundException("Review", "id", reviewId));
    }

    @GetMapping("/reviews/recipe/{recipeId}")
    public List<Review> getReviewsByRecipeId(@PathVariable(value = "recipeId") Long recipeId) {
        return reviewRepository.findByRecipeId(recipeId);
    }

    // UPDATE REVIEW ----------------------------
    @PutMapping("/reviews/{id}")
    public Review updateReview(@PathVariable(value = "id") Long reviewId,
                               @Valid @RequestBody Review reviewDetails) {

        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ResourceNotFoundException("Review", "id", reviewId));

        review.setComment(reviewDetails.getComment());
        review.setEditedAt(new Date());
        review.setRating(reviewDetails.getRating());

        Review updatedReview = reviewRepository.save(review);
        return updatedReview;
    }
    // DELETE REVIEW ------------------------------
    @DeleteMapping("/reviews/{id}")
    public ResponseEntity<?> deleteReview(@PathVariable(value = "id") Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ResourceNotFoundException("Review", "id", reviewId));
        reviewRepository.delete(review);
        return ResponseEntity.ok().build();
    }

    // Exception Class
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public class ResourceNotFoundException extends RuntimeException {
        private String resourceName;
        private String fieldName;
        private Object fieldValue;

        public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
            super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue));
            this.resourceName = resourceName;
            this.fieldName = fieldName;
            this.fieldValue = fieldValue;
        }
        public String getResourceName() {
            return resourceName;
        }
        public String getFieldName() {
            return fieldName;
        }
        public Object getFieldValue() {
            return fieldValue;
        }
    }
}

