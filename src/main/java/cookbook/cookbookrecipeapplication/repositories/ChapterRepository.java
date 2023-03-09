package cookbook.cookbookrecipeapplication.repositories;

import cookbook.cookbookrecipeapplication.models.Chapter;
import cookbook.cookbookrecipeapplication.models.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;


public interface ChapterRepository extends JpaRepository<Chapter, Long> {

    Chapter findByName(String name);
    Set<Chapter> getChaptersBySavedRecipes(Optional<Recipe> recipe);
}
