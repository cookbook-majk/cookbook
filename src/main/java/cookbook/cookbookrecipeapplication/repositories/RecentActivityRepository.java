package cookbook.cookbookrecipeapplication.repositories;

import cookbook.cookbookrecipeapplication.models.Follower;
import cookbook.cookbookrecipeapplication.models.RecentActivity;
import cookbook.cookbookrecipeapplication.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecentActivityRepository extends JpaRepository<RecentActivity, Long> {

}
