package mutsa.session4.service;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mutsa.session4.dto.FollowResponseDto;
import mutsa.session4.dto.UserResponseDto;
import mutsa.session4.entity.Follow;
import mutsa.session4.entity.User;
import mutsa.session4.repository.FollowRepository;
import mutsa.session4.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;
    private final UserRepository userRepository;


    public Long followToggle(Long fromId, Long toId) {
        if(fromId == toId){
            throw new IllegalStateException("본인은 팔로우 할 수 없습니다");
        }
        User fromUser = userRepository.findById(fromId)
            .orElseThrow(() -> new IllegalStateException("해당 fromId를 id로 가지는 유저가 존재하지 않습니다."));
        User toUser = userRepository.findById(toId)
            .orElseThrow(() -> new IllegalStateException("해당 toId를 id로 가지는 유저가 존재하지 않습니다."));
        if(followRepository.findByFromUserIdAndToUserId(fromUser.getId(), toUser.getId()).isPresent()) {
            Follow follow = followRepository.findByFromUserIdAndToUserId(fromUser.getId(),
                toUser.getId()).get();
            followRepository.delete(follow);
            return -1L;
        }else{
            Follow follow = new Follow(fromUser, toUser);
            return followRepository.save(follow).getId();
        }
    }

    public List<UserResponseDto> getFollowersInfo(Long userId) {
        List<Follow> byToId = followRepository.findByToUserId(userId);
        List<UserResponseDto> followerList = new ArrayList<>();
        byToId.forEach(follow -> {
            followerList.add(UserResponseDto.of(follow.getFromUser()));
        });

        return followerList;
    }

    public List<UserResponseDto> getFollowingInfo(Long userId) {
        List<Follow> byFromId = followRepository.findByFromUserId(userId);
        List<UserResponseDto> followingList = new ArrayList<>();
        byFromId.forEach(follow -> {
            followingList.add(UserResponseDto.of(follow.getToUser()));
        });
        return followingList;
    }

    public List<FollowResponseDto> getAllFollowInfo() {
        List<FollowResponseDto> followResponseDtoList = new ArrayList<>();
        followRepository.findAll()
            .forEach(follow -> followResponseDtoList.add(FollowResponseDto.of(follow)));
        return followResponseDtoList;
    }

}
