package cookbook.cookbookrecipeapplication.services;

import cookbook.cookbookrecipeapplication.models.Chapter;
import cookbook.cookbookrecipeapplication.models.Follower;
import cookbook.cookbookrecipeapplication.models.User;
import cookbook.cookbookrecipeapplication.repositories.ChapterRepository;
import cookbook.cookbookrecipeapplication.repositories.FollowerRepository;
import cookbook.cookbookrecipeapplication.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserDaoService {
    private final UserRepository userDao;
    private final PasswordEncoder passwordEncoder;
    private final ChapterRepository chapterDao;
    private final FollowerRepository followerDao;

    public UserDaoService(UserRepository userDao, PasswordEncoder passwordEncoder, ChapterRepository chapterDao, FollowerRepository followerDao) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.chapterDao = chapterDao;
        this.followerDao = followerDao;
    }

    public void registerUser(User user) {
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        user.setProfilePicture("example.jpg");
        user.setUserCreated(new Date());
        userDao.save(user);
        Chapter newChapter = new Chapter(user.getUsername() + "Saved", user.getUsername() + " saved recipes", user, false);
        chapterDao.save(newChapter);
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
}

    public void followUser(Follower follower){
        followerDao.save(follower);
    }

    public void unfollowUser(User loggedInUser, User followee){
        Follower follower = followerDao.findFollowerByFollowerAndUser(loggedInUser, followee);
        System.out.println(loggedInUser.getUsername());
        System.out.println(followee.getUsername());
        followerDao.delete(follower);
    }

}

