package cookbook.cookbookrecipeapplication.services;

import cookbook.cookbookrecipeapplication.repositories.ChapterRepository;
import cookbook.cookbookrecipeapplication.repositories.FollowerRepository;
import cookbook.cookbookrecipeapplication.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ReviewDaoService {

    private final UserRepository userDao;
    private final PasswordEncoder passwordEncoder;
    private final ChapterRepository chapterDao;
    private final FollowerRepository followerDao;

    public ReviewDaoService(UserRepository userDao, PasswordEncoder passwordEncoder, ChapterRepository chapterDao, FollowerRepository followerDao) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.chapterDao = chapterDao;
        this.followerDao = followerDao;
    }

}
