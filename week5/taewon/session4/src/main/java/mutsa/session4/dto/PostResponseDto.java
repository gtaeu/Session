package mutsa.session4.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import mutsa.session4.entity.Post;

@Data
@Builder
public class PostResponseDto {

    private Long postId;

    private String title;
    private String content;

    private Long view;

    private String username;


    public static PostResponseDto of(Post post) {
        return PostResponseDto.builder().postId(post.getId()).title(post.getTitle())
            .content(post.getContent()).view(post.getView()).username(post.getUser().getUsername())
            .build();
    }
}
