package mutsa.practice.mutsasession2.service;

import lombok.RequiredArgsConstructor;
import mutsa.practice.mutsasession2.entity.Article;
import mutsa.practice.mutsasession2.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    public Long createArticle(Article article) {
        return articleRepository.save(article);
    }

    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    public Article findById(Long id) {
        return articleRepository.findById(id);
    }
}
