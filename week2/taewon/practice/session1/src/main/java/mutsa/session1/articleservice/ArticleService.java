package mutsa.session1.articleservice;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    public Integer save(Article article) {
        return articleRepository.save(article);
    }

    public List<Article> findArticles() {
        return articleRepository.findArticles();
    }

    public Article findArticleById(int id) {
        return articleRepository.findArticleById(id);
    }

    public List<Article> findByTitle(String title) {
        return articleRepository.findByTitle(title);
    }
}
