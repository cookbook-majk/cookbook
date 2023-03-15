package cookbook.cookbookrecipeapplication.repositories;

import cookbook.cookbookrecipeapplication.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long>{
    User findByUsername(String username);

    @Query(value = "SELECT id from users u where u.first_name LIKE %?1% OR u.last_name LIKE %?1% OR u.username LIKE %?1%", nativeQuery = true)
    List<Long> findUserByFirstNameOrLastNameOrUsername(String searchTerm);

}
