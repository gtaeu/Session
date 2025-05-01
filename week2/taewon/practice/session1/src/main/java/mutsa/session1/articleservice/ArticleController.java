package mutsa.session1.articleservice;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @PostMapping("/api/v1")
    public Integer create(@RequestBody Article article) {
        return articleService.save(article);
    }

    @GetMapping("/api/v1/articles")
    public List<Article> readAll() {
        return articleService.findArticles();
    }

    @GetMapping("/api/v1/articles/{articleId}")
    public Article readById(@PathVariable int articleId) {
        return articleService.findArticleById(articleId);
    }

    @GetMapping("/api/v1/articles/title")
    public List<Article> readByTitle(@RequestParam String title) {
        return articleService.findByTitle(title);
    }
}
