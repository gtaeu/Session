package mutsa.mutsa_session_practice.domain;

import lombok.Getter;

@Getter

public class Article {
    private String title;
    private String content;

    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
