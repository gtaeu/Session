package mutsaweek2.demo.service;

import lombok.RequiredArgsConstructor;
import mutsaweek2.demo.Article;
import mutsaweek2.demo.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {
    /*
    게시글 작성, 목록 조회, 게시글 단건 조회
     */
    private final ArticleRepository articleRepository;

    public long writeArticle(Article article) {
        return articleRepository.save(article);
    }

    public List<Article> findByTitle(String title) {
        return articleRepository.findByTitle(title);
    }

    public Article findById(long id) {
        return articleRepository.findById(id);
    }

    public List<Article> findAll() {
        return articleRepository.findAll();
    }

}
