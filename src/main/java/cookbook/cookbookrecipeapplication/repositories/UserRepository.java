package cookbook.cookbookrecipeapplication.repositories;

import cookbook.cookbookrecipeapplication.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long>{
    User findByUsername(String username);
    @Query("SELECT DISTINCT user.id FROM User user " + "WHERE LOWER(user.username) LIKE %:searchTerm% " + "OR LOWER(user.firstName) LIKE %:searchTerm% " + "OR LOWER(user.lastName) LIKE %:searchTerm%")
    List<Long> findUserBySearchTerm(String searchTerm);

//    @Query("SELECT * from users u where u.first_name LIKE  %?1% OR u.last_name LIKE %?1% OR u.username LIKE %?1%")
//    List<User> findUserByFirstNameOrLastNameOrUsername(String searchTerm);

}


//@Query(value = "SELECT * FROM recipes r WHERE r.title LIKE %?1%", nativeQuery = true)
