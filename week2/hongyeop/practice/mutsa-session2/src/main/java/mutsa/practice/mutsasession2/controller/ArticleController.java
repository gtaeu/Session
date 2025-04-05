package mutsa.practice.mutsasession2.controller;

import lombok.RequiredArgsConstructor;
import mutsa.practice.mutsasession2.entity.Article;
import mutsa.practice.mutsasession2.service.ArticleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    @GetMapping("api/v1/articles")
    public ResponseEntity<List<Article>> getAllArticles() {
        return ResponseEntity.ok(articleService.findAll());
    }

    @PostMapping("api/v1/articles")
    public ResponseEntity<Long> createArticle(@RequestBody Article article) {
        return ResponseEntity.status(201).body(articleService.createArticle(article));
    }

    @GetMapping("api/v1/article/{articleId}")
    public ResponseEntity<Article> getArticle(@PathVariable Long articleId) {
        return ResponseEntity.ok(articleService.findById(articleId));
    }

}