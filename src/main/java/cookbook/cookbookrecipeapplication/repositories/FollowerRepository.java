package cookbook.cookbookrecipeapplication.repositories;

import cookbook.cookbookrecipeapplication.models.Follower;
import cookbook.cookbookrecipeapplication.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowerRepository extends JpaRepository<Follower, Long> {

    Follower findFollowerByFollowerAndUser(User follower, User user);

}
