package mutsa.practice.mutsasession2.repository;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import mutsa.practice.mutsasession2.entity.Article;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Getter
@Slf4j
public class ArticleRepository {
    private long PRIMARY_ID = 1;

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
}
