package mutsa.session4.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mutsa.session4.dto.FollowRequestDto;
import mutsa.session4.dto.FollowResponseDto;
import mutsa.session4.dto.UserResponseDto;
import mutsa.session4.service.FollowService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class FollowController {

    private final FollowService followService;

    @PostMapping("/follow")
    public ResponseEntity<Long> followToggle(@RequestBody FollowRequestDto followRequestDto) {
        return ResponseEntity.status(200)
            .body(followService.followToggle(followRequestDto.getFromId(),
                followRequestDto.getToId()));
    }


    @GetMapping("/follow/{userId}/followings")
    public ResponseEntity<List<UserResponseDto>> getUsersFollowingsInfo(@PathVariable Long userId) {
        return ResponseEntity.status(200).body(followService.getFollowingInfo(userId));
    }

    @GetMapping("/follow/{userId}/followers")
    public ResponseEntity<List<UserResponseDto>> getUsersFollowersInfo(@PathVariable Long userId) {
        return ResponseEntity.status(200).body(followService.getFollowersInfo(userId));
    }

    @GetMapping("/follow")
    public ResponseEntity<List<FollowResponseDto>> readAllFollowInfo() {
        return ResponseEntity.status(200).body(followService.getAllFollowInfo());
    }
}
