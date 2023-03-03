package cookbook.cookbookrecipeapplication.services;


import cookbook.cookbookrecipeapplication.repositories.ChapterRepository;
import org.springframework.stereotype.Service;

@Service
public class ChapterDaoService {

    private final ChapterRepository chapterRepository;


    public ChapterDaoService(ChapterRepository chapterRepository) {
        this.chapterRepository = chapterRepository;
    }
}
