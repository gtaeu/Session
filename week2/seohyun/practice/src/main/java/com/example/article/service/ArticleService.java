package com.example.article.service;

import com.example.article.domain.Article;
import com.example.article.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    public Long createArticle(Article article) {
        return articleRepository.save(article);
    }

    public Article findById(Long id) {
        return articleRepository.findById(id);
    }

    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    public List<Article> findByTitle(String title) {
        return articleRepository.findAllByTitle(title);
    }

}
