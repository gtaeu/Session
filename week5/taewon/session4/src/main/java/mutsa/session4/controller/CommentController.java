package mutsa.session4.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mutsa.session4.dto.CommentRequestDto;
import mutsa.session4.dto.CommentResponseDto;
import mutsa.session4.service.CommentService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/comments/{postId}/{userId}/{commentId}") //commentId를 전송하여 해당 댓글에 대한 대댓글로 생성
    public ResponseEntity<Long> createNestedComment(@PathVariable Long postId, @PathVariable Long userId,
        @PathVariable Long commentId,
        @RequestBody
        CommentRequestDto commentRequestDto) {
        return ResponseEntity.status(200)
            .body(commentService.createNestedComment(postId, userId, commentId, commentRequestDto));
    }

    @PostMapping("/comments/{postId}/{userId}") //commentId를 전송하여 해당 댓글에 대한 대댓글로 생성
    public ResponseEntity<Long> createComment(@PathVariable Long postId, @PathVariable Long userId,
        @RequestBody
        CommentRequestDto commentRequestDto) {
        return ResponseEntity.status(200)
            .body(commentService.createComment(postId, userId, commentRequestDto));
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<Void> delteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.status(200).body(null);
    }

    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<List<CommentResponseDto>> readCommentByPostId(@PathVariable Long postId) {
        return ResponseEntity.status(200).body(commentService.readCommentsByPostId(postId));
    }

    //특정 게시글에 대한 댓글 수정
    @PutMapping("/comments/{commentId}")
    public ResponseEntity<CommentResponseDto> updateComment(@PathVariable Long commentId,
        @RequestBody CommentRequestDto commentRequestDto) {
        return ResponseEntity.status(200)
            .body(commentService.updateComment(commentId, commentRequestDto));
    }

    @GetMapping("/comments/{commentId}/replies")
    public ResponseEntity<List<CommentResponseDto>> readNestedCommentsById(
        @PathVariable Long commentId) {
        return ResponseEntity.status(200)
            .body(commentService.readNestedCommentByCommentId(commentId));
    }
}
