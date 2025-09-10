package mutsa.session4.service;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mutsa.session4.dto.PostResponseDto;
import mutsa.session4.dto.UserResponseDto;
import mutsa.session4.dto.UserResponseDto.UserResponseDtoBuilder;
import mutsa.session4.entity.Like;
import mutsa.session4.entity.Post;
import mutsa.session4.entity.User;
import mutsa.session4.repository.LikeRepository;
import mutsa.session4.repository.PostRepository;
import mutsa.session4.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public String likesToggle(Long postId,Long userId) {
        Like like = likeRepository.findByUserIdAndPostId(userId, postId);
        if (like != null) {
            likeRepository.delete(like);
            return "좋아요 취소";
        }else {
            User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException("해당 Id를 가진 유저가 존재하지 않습니다."));
            Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalStateException("해당 Id를 가진 게시글이 존재하지 않습니다."));
            like = Like.builder().post(post).user(user).build();
            Like savedLike = likeRepository.save(like);
            return "좋아요 생성";
        }
    }

    public List<UserResponseDto> findlikeUserByPostId(Long postId) {
        List<UserResponseDto> result = new ArrayList<>();
        likeRepository.findByPostId(postId).forEach(like -> {
            User user = like.getUser();
            UserResponseDto userResponseDto = UserResponseDto.builder().userId(user.getId())
                .username(user.getUsername()).build();
            result.add(userResponseDto);
        });
        return result;
    }

    public List<PostResponseDto> findlikedPostByUserId(Long userId) {
        List<PostResponseDto> result = new ArrayList<>();
        likeRepository.findByUserId(userId).forEach(like -> {
            result.add(PostResponseDto.of(like.getPost()));
        });
        return result;
    }

    public Boolean isLiked(Long postId, Long userId) {
        Like like = likeRepository.findByUserIdAndPostId(userId, postId);
        if (like != null) {
            return true;
        }
        return false;
    }

}
