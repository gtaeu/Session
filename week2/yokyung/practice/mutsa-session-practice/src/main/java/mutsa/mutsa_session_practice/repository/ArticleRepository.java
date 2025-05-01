package mutsa.mutsa_session_practice.repository;

import lombok.extern.slf4j.Slf4j;
import mutsa.mutsa_session_practice.domain.Article;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Slf4j
public class ArticleRepository {
    private long PRIMARY_ID = 1;

    //DB
    private Map<Long, Article> database = new HashMap<>();

    public Long save(Article article) {
        log.info("Primary ID: " + PRIMARY_ID);
        database.put(PRIMARY_ID, article);
        return PRIMARY_ID++;
    }


    public Article findById(Long id) {
        return database.get(id);
    }

    public List<Article> findAll() {
        return new ArrayList<>(database.values());
    }

    public List<Article> findByTitle(String title) {
        List<Article> articles = new ArrayList<>();
        for (Article article : database.values()) {
            if (article.getTitle().equals(title)) {
                articles.add(article);
            }
        }
        return articles;
    }

    public void tearDown(){ //Repository의 저장소 상태를 “초기화”하는 용도로 만든 메서드. 보통 테스트 코드에서 사용
        database.clear();
        PRIMARY_ID = 1;
    }
}
