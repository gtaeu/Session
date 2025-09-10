package mutsa.session4.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mutsa.session4.dto.PostRequestDto;
import mutsa.session4.dto.PostResponseDto;
import mutsa.session4.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/posts")
    public ResponseEntity<Long> createPost(@RequestBody PostRequestDto postRequestDto) {
        return ResponseEntity.status(201).body(postService.createPost(postRequestDto));
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<PostResponseDto> readPost(@PathVariable Long id) {
        return ResponseEntity.status(200).body(postService.readPost(id));
    }

    @GetMapping("/posts")
    public ResponseEntity<List<PostResponseDto>> readPosts() {
        return ResponseEntity.status(200).body(postService.readPosts());
    }

    @GetMapping("/users/{userId}/posts")
    public ResponseEntity<List<PostResponseDto>> readPostsByUserId(@PathVariable Long userId) {
        return ResponseEntity.status(200).body(postService.readPostsByUserId(userId));
    }
}
