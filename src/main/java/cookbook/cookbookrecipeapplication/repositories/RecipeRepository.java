package cookbook.cookbookrecipeapplication.repositories;

import cookbook.cookbookrecipeapplication.models.CustomRecipe;
import cookbook.cookbookrecipeapplication.models.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    Recipe findBySpoonacularId(long spoonacularId);

    @Query(value = "SELECT * FROM recipes r WHERE r.title LIKE %?1%", nativeQuery = true)
    List<Recipe> findRecipesByTitle(String searchTerm);

}
