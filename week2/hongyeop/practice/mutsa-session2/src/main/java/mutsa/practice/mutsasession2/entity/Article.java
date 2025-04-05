package mutsa.practice.mutsasession2.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Article {
    private String title;
    private String description;

    public Article(String title, String description) {
        this.title = title;
        this.description = description;
    }
}
