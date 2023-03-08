package cookbook.cookbookrecipeapplication.repositories;

import cookbook.cookbookrecipeapplication.models.Chapter;
import cookbook.cookbookrecipeapplication.models.DishType;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DishTypeRepository extends JpaRepository<DishType, Long> {

}
