package mutsa.session4.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Getter
public class PostRequestDto {

    private String title;

    private String content;

    private Long userId; //로그인 기능이 없으므로 userId 전달 받아 게시글에 유저 설정
}
