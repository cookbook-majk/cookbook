package cookbook.cookbookrecipeapplication.repositories;

import cookbook.cookbookrecipeapplication.models.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    Recipe findBySpoonacularId(long spoonacularId);

    @Query(value = "SELECT recipe_id FROM recent_activity WHERE activity_type = 2 AND created_at >= DATE_SUB(NOW(), INTERVAL 2 WEEK) GROUP BY recipe_id ORDER BY COUNT(*) DESC LIMIT 20", nativeQuery = true)
    List<Long> findTrendingRecipeIds();
    @Query(value = "SELECT recipe_id, COUNT(*) AS activity_count FROM recent_activity WHERE activity_type = 2 GROUP BY recipe_id ORDER BY activity_count DESC LIMIT 20;", nativeQuery = true)
    List<Long> findMostSavedRecipeIds();

}
