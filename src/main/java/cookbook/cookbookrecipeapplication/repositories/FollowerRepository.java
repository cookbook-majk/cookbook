package cookbook.cookbookrecipeapplication.repositories;

import cookbook.cookbookrecipeapplication.models.Follower;
import cookbook.cookbookrecipeapplication.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FollowerRepository extends JpaRepository<Follower, Long> {

    Follower findFollowerByFollowerAndUser(User follower, User user);

    @Query(value = "SELECT follower_id from followers a where a.user_id = ?1", nativeQuery = true)
    List<Long> getFollowersByUser(long userId);

}
