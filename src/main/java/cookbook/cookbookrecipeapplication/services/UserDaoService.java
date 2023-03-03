package cookbook.cookbookrecipeapplication.services;
//
//import cookbook.cookbookrecipeapplication.models.Chapter;
//import cookbook.cookbookrecipeapplication.models.User;
//import cookbook.cookbookrecipeapplication.repositories.ChapterRepository;
import cookbook.cookbookrecipeapplication.repositories.UserRepository;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
//
@Service
public class UserDaoService {
    private final UserRepository userDao;
//    private final PasswordEncoder passwordEncoder;
//    private final ChapterRepository chapterDao;
//
//    public UserDaoService(UserRepository userDao, PasswordEncoder passwordEncoder, ChapterRepository chapterDao) {
//        this.userDao = userDao;
//        this.passwordEncoder = passwordEncoder;
//        this.chapterDao = chapterDao;
//    }
//

    public UserDaoService(UserRepository userDao) {
        this.userDao = userDao;
    }


//    public void registerUser(User user){
//        String hash = passwordEncoder.encode(user.getPassword());
//        user.setPassword(hash);
//        userDao.save(user);
//        Chapter newChapter = new Chapter(user.getUsername() + "Saved", user.getUsername() + " saved recipes", user, false);
//        chapterDao.save(newChapter);
//    }
}
