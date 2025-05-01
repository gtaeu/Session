package mutsaweek2.demo.repository;

import jakarta.persistence.EntityManager;
import lombok.Getter;
import mutsaweek2.demo.Article;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Getter
public class ArticleRepository {
    /*Repository class에 포함해야할것
    1. 데이터베이스 연결 설정(여기서는 HaspMap기능 사용) or entitymanager 선언
    2. save(저장 기능)
    3. 조회 메서드(DB를 통한 것이므로 repository에서 사용)
        ex) findAll, findById 등
     */

    private long id = 1;
    private Map<Long, Article> articleDB = new HashMap<>();

    public Long save(Article article) {
        articleDB.put(id,article);
        return id++;
    }

    public List<Article> findByTitle(String title) {
        List<Article> articles = new ArrayList<>();
        for (Article article : articleDB.values()) {
            if(article.getTitle().contains(title)) {
                //단어가 타이틀에 있으면 나오도록
                articles.add(article);
            }
        }
        return articles;
    }

    public Article findById(long id) {
        return articleDB.get(id);
        //id라는 key를 찾아서 value(article) 전달
    }

    public List<Article> findAll(){
        return new ArrayList<>(articleDB.values());
    }

}
