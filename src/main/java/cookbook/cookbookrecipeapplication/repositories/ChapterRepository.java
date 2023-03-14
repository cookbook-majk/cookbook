package cookbook.cookbookrecipeapplication.repositories;

import cookbook.cookbookrecipeapplication.models.Chapter;
import cookbook.cookbookrecipeapplication.models.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.Set;


public interface ChapterRepository extends JpaRepository<Chapter, Long> {

    Chapter findByName(String name);

    @Query(value = "SELECT id from chapters c where c.user_id = ?1", nativeQuery = true)
    Chapter findByUserId(long id);

    Set<Chapter> getChaptersBySavedRecipes(Optional<Recipe> recipe);

}


//@Query(value = "SELECT * from recent_activity a where a.user_id = ?1 and a.recipe_id = ?2 and a.activity_type = 1", nativeQuery = true)
