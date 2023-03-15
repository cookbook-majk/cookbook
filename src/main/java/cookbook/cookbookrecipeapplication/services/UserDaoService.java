package cookbook.cookbookrecipeapplication.services;

import cookbook.cookbookrecipeapplication.models.*;
import cookbook.cookbookrecipeapplication.repositories.ChapterRepository;
import cookbook.cookbookrecipeapplication.repositories.FollowerRepository;
import cookbook.cookbookrecipeapplication.repositories.RecentActivityRepository;
import cookbook.cookbookrecipeapplication.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Service
public class UserDaoService {
    private final UserRepository userDao;
    private final PasswordEncoder passwordEncoder;
    private final ChapterRepository chapterDao;
    private final FollowerRepository followerDao;
    private final RecentActivityRepository recentActivityDao;

    public UserDaoService(UserRepository userDao, PasswordEncoder passwordEncoder, ChapterRepository chapterDao, FollowerRepository followerDao, RecentActivityRepository recentActivityDao) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.chapterDao = chapterDao;
        this.followerDao = followerDao;
        this.recentActivityDao = recentActivityDao;
    }

    public void registerUser(User user) {
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        user.setProfilePicture("https://cdn.filestackcontent.com/UHwsMtM8Qf2XXlhEIOON");
        user.setUserCreated(new Date());
        userDao.save(user);
        Chapter newChapter = new Chapter(user.getUsername() + "Saved", user.getUsername() + " saved recipes", user, false);
        chapterDao.save(newChapter);
    }

    public void editUser(User user){
        userDao.save(user);
    }

    public User findUserById(long id) {
        return userDao.findById(id).get();
    }

    public User findUserByUsername(String username) {
        return userDao.findByUsername(username);
    }

    // UPDATE USER

    // DELETE USER
    public void deleteUser(Long userId) {
        boolean idExists = userDao.existsById(userId);
        if (!idExists) {
            throw new IllegalStateException("User does not exist");
        }
        userDao.deleteById(userId);
    }

    public void saveRecentActivity(RecentActivity recentActivity){
        recentActivityDao.save(recentActivity);
    }

    public void followUser(Follower follower){
        followerDao.save(follower);
    }

    public void unfollowUser(User loggedInUser, User followee){
        Follower follower = followerDao.findFollowerByFollowerAndUser(loggedInUser, followee);
        System.out.println(loggedInUser.getUsername());
        System.out.println(followee.getUsername());
        followerDao.delete(follower);
        deleteRecentActivity(recentActivityDao.getRecentActivityByUserIdAndTargetUserId(followee.getId(), (loggedInUser.getId())));
    }

    public List<RecentActivity> getAllFollowingRecentActivity(User user){
        List<Long> followerIds = followerDao.getFollowersByUser(user.getId());
        List<RecentActivity> recentActivityList = new ArrayList<>();
        for (Long userId : followerIds){
            User follower = userDao.findById(userId).get();
            recentActivityList.addAll(follower.getRecentActivities());
        }
        Collections.sort(recentActivityList);
        return recentActivityList;
    }

    public boolean isUserFollowingUser(User user1, User user2){
        return (followerDao.findFollowerByFollowerAndUser(user1, user2)) != null;
    }

    public void deleteRecentActivity(RecentActivity recentActivity){
        recentActivityDao.delete(recentActivity);
    }

    public List<User> searchUsers(String searchTerm) {
        List<User> users = new ArrayList<>();
        for (Long id : userDao.findUserByFirstNameOrLastNameOrUsername(searchTerm)){
            users.add(findUserById(id));
        }
        return users;
    }



    public boolean checkIfSaved(User user, Recipe recipe){
        if (user != null) {
            return chapterDao.findByUserId(user.getId()).getSavedRecipes().contains(recipe);
        }
        return false;
    }

}

