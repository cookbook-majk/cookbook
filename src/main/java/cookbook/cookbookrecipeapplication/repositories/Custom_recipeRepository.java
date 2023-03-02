package cookbook.cookbookrecipeapplication.repositories;

import cookbook.cookbookrecipeapplication.models.Custom_recipe;
import cookbook.cookbookrecipeapplication.models.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Custom_recipeRepository extends JpaRepository<Custom_recipe, Long> {
}
