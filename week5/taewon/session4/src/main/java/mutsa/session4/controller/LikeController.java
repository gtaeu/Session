package mutsa.session4.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mutsa.session4.dto.PostResponseDto;
import mutsa.session4.dto.UserResponseDto;
import mutsa.session4.service.LikeService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;


    @PostMapping("/likes/{postId}/{userId}") //로그인 기능 없어서 userId를 path variable로 전달
    public ResponseEntity<String> likeToggle(@PathVariable Long postId, @PathVariable Long userId) {
        return ResponseEntity.status(200).body(likeService.likesToggle(postId, userId));
    }

    @GetMapping("/likes/post/{postId}") //Id가 postId인 게시글에 좋아요를 누른 회원 정보
    public ResponseEntity<List<UserResponseDto>> readLikeUserbyPostId(@PathVariable Long postId) {
        List<UserResponseDto> userResponseDtos = likeService.findlikeUserByPostId(postId);
        return ResponseEntity.status(200).body(userResponseDtos);
    }

    @GetMapping("/likes/user/{userId}") //유저가 좋아하는 post 리스트
    public ResponseEntity<List<PostResponseDto>> readLikedPostByUserId(@PathVariable Long userId) {
        List<PostResponseDto> postResponseDtos = likeService.findlikedPostByUserId(userId);
        return ResponseEntity.status(200).body(postResponseDtos);
    }

    @GetMapping("/likes/{postId}/{userId}")
    public ResponseEntity<Boolean> isLiked(@PathVariable Long postId, @PathVariable Long userId) {
        return ResponseEntity.status(200).body(likeService.isLiked(postId, userId));
    }
}
