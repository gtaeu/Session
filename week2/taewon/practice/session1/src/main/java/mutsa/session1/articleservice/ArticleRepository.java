package mutsa.session1.articleservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public class ArticleRepository {

    private static int articleNum = 0;
    private final Map<Integer, Article> articleMap = new HashMap<>();

    public int save(Article article) {
        articleMap.put(++articleNum, article);
        return articleNum;
    }

    public List<Article> findArticles() {
        return new ArrayList<>(articleMap.values());
    }

    public Article findArticleById(int id) {
        return articleMap.get(id);
    }

    public List<Article> findByTitle(String title) {
        return articleMap.values().stream().filter(article -> article.title.contains(title))
            .toList();
    }
}