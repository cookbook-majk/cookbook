package cookbook.cookbookrecipeapplication.services;


import cookbook.cookbookrecipeapplication.models.Chapter;
import cookbook.cookbookrecipeapplication.models.User;
import cookbook.cookbookrecipeapplication.repositories.ChapterRepository;
import org.springframework.stereotype.Service;

@Service
public class ChapterDaoService {

    private final ChapterRepository chapterDao;


    public ChapterDaoService(ChapterRepository chapterDao) {
        this.chapterDao = chapterDao;
    }

    public Chapter findSavedChapterByUser(User user){
        return chapterDao.findByName(user.getUsername() + "Saved");
    }

    public void saveChapter(Chapter chapter){
        chapterDao.save(chapter);
    }


}
