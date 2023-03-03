package cookbook.cookbookrecipeapplication.repositories;

import cookbook.cookbookrecipeapplication.models.CustomRecipe;
import cookbook.cookbookrecipeapplication.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomRecipeRepository extends JpaRepository<CustomRecipe, Long> {

    List<CustomRecipe> findCustomRecipesByUser(User user);

}
