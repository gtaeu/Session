package mutsa.session4.service;

import lombok.RequiredArgsConstructor;
import mutsa.session4.dto.UserRequestDto;
import mutsa.session4.dto.UserResponseDto;
import mutsa.session4.entity.User;
import mutsa.session4.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Long createUser(UserRequestDto userRequestDto) {
        if(userRepository.findByUsername(userRequestDto.getUsername()).isPresent()){
            throw new IllegalStateException("동일한 유저네임을 가진 유저가 존재 합니다.");
        }
        User user = User.builder().username(userRequestDto.getUsername())
            .password(userRequestDto.getPassword())
            .build();

        return userRepository.save(user).getId();
    }

    public UserResponseDto findById(Long userId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new IllegalStateException("해당 Id를 가진 유저가 존재하지 않습니다."));
        return UserResponseDto.of(user);
    }

}
