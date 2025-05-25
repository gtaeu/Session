package mutsa.session4.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import mutsa.session4.entity.Comment;
import mutsa.session4.entity.User;

@Data
@Builder
public class CommentResponseDto {

    private Long id;
    private String content;
    private List<CommentResponseDto> childs = new ArrayList<>();
    private UserResponseDto userResponseDto;

    public static CommentResponseDto of(Comment comment) {

        User user = comment.getUser();
        UserResponseDto userResponseDto = UserResponseDto.builder().userId(user.getId())
            .username(user.getUsername())
            .build();

        CommentResponseDto commentResponseDto = CommentResponseDto.builder()
            .id(comment.getId())
            .content(comment.getContent())
            .userResponseDto(userResponseDto)
            .build();
        List<CommentResponseDto> list = new ArrayList<>();
        comment.getChilds().forEach(c -> {
            list.add(CommentResponseDto.of(c));
        });
        commentResponseDto.setChilds(list);
        return commentResponseDto;
    }
}
