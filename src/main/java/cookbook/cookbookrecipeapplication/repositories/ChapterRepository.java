package cookbook.cookbookrecipeapplication.repositories;

import cookbook.cookbookrecipeapplication.models.Chapter;
import cookbook.cookbookrecipeapplication.models.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.Set;


public interface ChapterRepository extends JpaRepository<Chapter, Long> {

    Chapter findByName(String name);

    Set<Chapter> getChaptersBySavedRecipes(Optional<Recipe> recipe);

    @Query(value = "SELECT * from chapters c where c.user_id = ?1", nativeQuery = true)
    Chapter findByUserId(long id);

}
