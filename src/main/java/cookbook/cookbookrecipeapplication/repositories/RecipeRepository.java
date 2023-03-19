package cookbook.cookbookrecipeapplication.repositories;

import cookbook.cookbookrecipeapplication.models.CustomRecipe;
import cookbook.cookbookrecipeapplication.models.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    Recipe findBySpoonacularId(long spoonacularId);

    Recipe findByTitle(String title);

    @Query(value = "SELECT recipe_id FROM recent_activity WHERE activity_type = 2 AND created_at >= DATE_SUB(NOW(), INTERVAL 2 WEEK) GROUP BY recipe_id ORDER BY COUNT(*) DESC LIMIT 12", nativeQuery = true)
    List<Long> findTrendingRecipeIds();
    
    @Query(value = "SELECT recipe_id, COUNT(*) AS activity_count FROM recent_activity WHERE activity_type = 2 GROUP BY recipe_id ORDER BY activity_count DESC LIMIT 12;", nativeQuery = true)
    List<Long> findMostSavedRecipeIds();

    @Query(value = "SELECT * FROM recipes r WHERE r.title LIKE %?1%", nativeQuery = true)
    List<Recipe> findRecipesByTitle(String searchTerm);

    @Query(value = "SELECT recipes FROM recipe_types r WHERE r.dish_types = ?1", nativeQuery = true)
    List<Long> findRecipesByDishType(long dishTypeId);

}
