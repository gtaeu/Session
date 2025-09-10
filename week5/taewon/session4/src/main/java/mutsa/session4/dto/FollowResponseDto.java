package mutsa.session4.dto;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import mutsa.session4.entity.Follow;

@Getter
@Setter
@Builder
public class FollowResponseDto {

    Long id;
    UserResponseDto fromUser;
    UserResponseDto toUser;

    public static FollowResponseDto of(Follow follow) {
        return FollowResponseDto.builder()
            .id(follow.getId())
            .fromUser(UserResponseDto.of(follow.getFromUser()))
            .toUser(UserResponseDto.of(follow.getToUser()))
            .build();
    }
}
