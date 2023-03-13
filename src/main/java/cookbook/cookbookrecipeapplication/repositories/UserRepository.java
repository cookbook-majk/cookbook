package cookbook.cookbookrecipeapplication.repositories;

import cookbook.cookbookrecipeapplication.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long>{
    User findByUsername(String username);
    @Query("SELECT DISTINCT user.id FROM User user " + "WHERE LOWER(user.username) LIKE %:searchTerm% " + "OR LOWER(user.firstName) LIKE %:searchTerm% " + "OR LOWER(user.lastName) LIKE %:searchTerm%")
    List<Long> findUserBySearchTerm(String searchTerm);

}
