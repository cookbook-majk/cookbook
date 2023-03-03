package cookbook.cookbookrecipeapplication.services;

import cookbook.cookbookrecipeapplication.models.Chapter;
import cookbook.cookbookrecipeapplication.models.User;
import cookbook.cookbookrecipeapplication.repositories.ChapterRepository;
import cookbook.cookbookrecipeapplication.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserDaoService {
    private final UserRepository userDao;
    private final PasswordEncoder passwordEncoder;
    private final ChapterRepository chapterDao;

    public UserDaoService(UserRepository userDao, PasswordEncoder passwordEncoder, ChapterRepository chapterDao) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.chapterDao = chapterDao;
    }

    public void registerUser(User user){
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        user.setProfilePicture("example.jpg");
        user.setUserCreated(new Date());
        userDao.save(user);
        Chapter newChapter = new Chapter(user.getUsername() + "Saved", user.getUsername() + " saved recipes", user, false);
        chapterDao.save(newChapter);
    }

    public User findUserById(long id){
        return userDao.findById(id).get();
    }

}