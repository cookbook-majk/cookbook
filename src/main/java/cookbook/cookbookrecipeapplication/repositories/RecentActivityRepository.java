package cookbook.cookbookrecipeapplication.repositories;

import cookbook.cookbookrecipeapplication.models.Follower;
import cookbook.cookbookrecipeapplication.models.RecentActivity;
import cookbook.cookbookrecipeapplication.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RecentActivityRepository extends JpaRepository<RecentActivity, Long> {

//    public RecentActivity findRecentActivityByUserAndTarget_user(User user, User targetUser);

//    @Query(value = "SELECT * from RecentActivity a where a.user like ?1 and a.target_user like?2")
//    List<RecentActivity> getRecentActivityByUserIdAndTargetUserId(long userId, long targetUserId);

}
