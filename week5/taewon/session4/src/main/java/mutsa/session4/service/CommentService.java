package mutsa.session4.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import mutsa.session4.dto.CommentRequestDto;
import mutsa.session4.dto.CommentResponseDto;
import mutsa.session4.entity.Comment;
import mutsa.session4.entity.Post;
import mutsa.session4.entity.User;
import mutsa.session4.repository.CommentRepository;
import mutsa.session4.repository.PostRepository;
import mutsa.session4.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public Long createNestedComment(Long postId, Long userId, Long commentId, CommentRequestDto commentRequestDto) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new IllegalStateException("해당 Id를 가진 유저가 존재하지 않습니다."));
        Post post = postRepository.findById(postId)
            .orElseThrow(() -> new IllegalStateException("해당 Id를 가진 게시글이 존재하지 않습니다."));
        Comment parent = commentRepository.findById(commentId)
            .orElseThrow(() -> new IllegalStateException("해당 Id를 가진 댓글이 존재하지 않습니다."));
        Comment comment = Comment.builder()
            .user(user)
            .post(post)
            .parent(parent)
            .content(commentRequestDto.getContent()).build();
        return commentRepository.save(comment).getId();
    }

    public Long createComment(Long postId, Long userId, CommentRequestDto commentRequestDto) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new IllegalStateException("해당 Id를 가진 유저가 존재하지 않습니다."));
        Post post = postRepository.findById(postId)
            .orElseThrow(() -> new IllegalStateException("해당 Id를 가진 게시글이 존재하지 않습니다."));
        Comment comment = Comment.builder()
            .user(user)
            .post(post)
            .content(commentRequestDto.getContent()).build();
        return commentRepository.save(comment).getId();
    }

    @Transactional
    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
            .orElseThrow(() -> new IllegalStateException("해당 id를 가진 댓글이 존재하지 않습니다."));
        commentRepository.delete(comment);
        for (Comment childComment : comment.getChilds()) {
            commentRepository.delete(childComment);
        }
    }

    public List<CommentResponseDto> readCommentsByPostId(Long postId) {
        List<CommentResponseDto> commentResponseDtoList = new ArrayList<>();
        List<Comment> comments = commentRepository.findByPostId(postId);
        comments.removeIf(comment -> comment.getParent() != null);
            comments
            .forEach(comment -> {
            commentResponseDtoList.add(CommentResponseDto.of(comment));});
        return commentResponseDtoList;
    }

    public CommentResponseDto updateComment(Long commentId, CommentRequestDto commentRequestDto) {
        Comment comment = commentRepository.findById(commentId)
            .orElseThrow(() -> new IllegalStateException("해당 id를 가진 댓글이 존재하지 않습니다."));
        comment.setContent(commentRequestDto.getContent());
        return CommentResponseDto.of(comment);
    }

    public List<CommentResponseDto> readNestedCommentByCommentId(Long parentId) {
        List<CommentResponseDto> commentResponseDtoList = new ArrayList<>();
        List<Comment> nestedComments = commentRepository.findByParentId(parentId);
        nestedComments
            .forEach(comment -> {
                commentResponseDtoList.add(CommentResponseDto.of(comment));});
        return commentResponseDtoList;
    }
}
