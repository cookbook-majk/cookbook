package cookbook.cookbookrecipeapplication.repositories;

import cookbook.cookbookrecipeapplication.models.CustomRecipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomRecipeRepository extends JpaRepository<CustomRecipe, Long> {
}
