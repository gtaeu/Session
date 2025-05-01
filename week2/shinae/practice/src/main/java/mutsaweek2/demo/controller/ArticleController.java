package mutsaweek2.demo.controller;

import lombok.RequiredArgsConstructor;
import mutsaweek2.demo.Article;
import mutsaweek2.demo.service.ArticleService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController//restapi의 진입점
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService; //서비스 계층 의존성주입

    @PostMapping("/articles")
    public ResponseEntity<Long> writeArticle(@RequestBody Article article) {
        return ResponseEntity.status(201).body(articleService.writeArticle(article));
    } // 게시글 작성에 필요한

    @GetMapping("/article/{articleId}")
    public ResponseEntity<Article> getArticle(@PathVariable Long articleId) {
        return ResponseEntity.ok(articleService.findById(articleId));
    }

    @GetMapping("/articles")
    public ResponseEntity<List<Article>> getAllArticles() {
        return ResponseEntity.ok(articleService.findAll());
    }

    @GetMapping("/articles/search")
    public ResponseEntity<List<Article>> searchArticles(@RequestParam String title) {
        return ResponseEntity.ok(articleService.findByTitle(title));
    }
}

