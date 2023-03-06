package cookbook.cookbookrecipeapplication.repositories;

import cookbook.cookbookrecipeapplication.models.Chapter;
import cookbook.cookbookrecipeapplication.models.Review;
import cookbook.cookbookrecipeapplication.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;


public interface ChapterRepository extends JpaRepository<Chapter, Long> {

    Chapter findByName(String name);
}
