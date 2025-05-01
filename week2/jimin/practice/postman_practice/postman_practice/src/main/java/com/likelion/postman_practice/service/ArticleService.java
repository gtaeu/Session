package com.likelion.postman_practice.service;

import com.likelion.postman_practice.domain.Article;
import com.likelion.postman_practice.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {
    /*
    1. 게시글 작성
    2. 게시글 목록 조회
    3. 게시글 단건 조회
     */

    private final ArticleRepository articleRepository;

    @Autowired
    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Long createArticle(Article article) {
        articleRepository.save(article);
        return article.getId();
    }

    public Article findOne(Long id) {
        return articleRepository.findById(id);
    }

    public List<Article> findAll() {
        return articleRepository.findAll();
    }
}
