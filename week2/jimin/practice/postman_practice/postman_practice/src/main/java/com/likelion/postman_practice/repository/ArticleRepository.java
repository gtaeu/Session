package com.likelion.postman_practice.repository;

import com.likelion.postman_practice.domain.Article;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ArticleRepository {

    private static Map<Long,Article> articleMap = new HashMap<Long,Article>();
    private static Long sequence = 0L;

    public Article save(Article article)
    {
        article.setId(++sequence);
        articleMap.put(article.getId(), article);
        return article;
    }

    public Article findById(Long id)
    {
        return articleMap.get(id);
    }

    public List<Article> findAll()
    {
        return new ArrayList<Article>(articleMap.values());
    }
}
