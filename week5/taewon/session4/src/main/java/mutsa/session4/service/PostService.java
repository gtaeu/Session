package mutsa.session4.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import mutsa.session4.dto.PostRequestDto;
import mutsa.session4.dto.PostResponseDto;
import mutsa.session4.entity.Post;
import mutsa.session4.entity.User;
import mutsa.session4.repository.PostRepository;
import mutsa.session4.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public Long createPost(PostRequestDto postRequestDto) {
        Optional<User> findUser = userRepository.findById(postRequestDto.getUserId());
        if (findUser.isPresent()) {
            Post post = Post.builder().title(postRequestDto.getTitle())
                .content(postRequestDto.getContent())
                .view(0L)
                .user(findUser.get()).build();
            Post savedPost = postRepository.save(post);
            return savedPost.getId();
        } else {
            throw new IllegalStateException("해당 username을 가진 유저가 존재하지 않습니다.");
        }
    }

    public PostResponseDto readPost(Long postId) {
        Post post = postRepository.findById(postId)
            .orElseThrow(() -> new IllegalStateException("해당 Id를 가진 post가 존재하지 않습니다"));
        return PostResponseDto.of(post);
    }

    public List<PostResponseDto> readPosts() {
        List<PostResponseDto> result = new ArrayList<>();
        postRepository.findAll().forEach(post -> {
            result.add(PostResponseDto.of(post));
        });
        return result;
    }

    public List<PostResponseDto> readPostsByUserId(Long userId) {
        List<PostResponseDto> result = new ArrayList<>();
        postRepository.findByUserId(userId)
            .forEach(post -> result.add(PostResponseDto.of(post)));
        return result;
    }
}


