package cookbook.cookbookrecipeapplication.repositories;

import cookbook.cookbookrecipeapplication.models.Follower;
import cookbook.cookbookrecipeapplication.models.RecentActivity;
import cookbook.cookbookrecipeapplication.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RecentActivityRepository extends JpaRepository<RecentActivity, Long> {

//    public RecentActivity findRecentActivityByUserAndTarget_user(User user, User targetUser);

    @Query(value = "SELECT * from recent_activity a where a.user_id = ?1 and a.target_user_id = ?2", nativeQuery = true)
    RecentActivity getRecentActivityByUserIdAndTargetUserId(long userId, long targetUserId);

    @Query(value = "SELECT * from recent_activity a where a.user_id = ?1 and a.recipe_id = ?2 and a.activity_type = 1", nativeQuery = true)
    RecentActivity getRecentCreatedActivityByUserIdAndRecipe(long userId, long recipeId);

    @Query(value = "SELECT * from recent_activity a where a.user_id = ?1 and a.recipe_id = ?2 and a.activity_type = 2", nativeQuery = true)
    RecentActivity getRecentSavedActivityByUserIdAndRecipe(long userId, long recipeId);

    @Query(value = "SELECT * from recent_activity a where a.review_id = ?1", nativeQuery = true)
    RecentActivity getRecentReviewActivityByUserIdAndRecipe(long reviewId);
}
