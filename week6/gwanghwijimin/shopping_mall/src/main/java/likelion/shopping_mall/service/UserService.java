package likelion.shopping_mall.service;

import likelion.shopping_mall.dto.request.UserRequest;
import likelion.shopping_mall.entity.Cart;
import likelion.shopping_mall.entity.User;
import likelion.shopping_mall.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // 유저 생성
    public Long createUser(UserRequest request) {
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .password(request.getPassword())
                .address(request.getAddress())
                .build();

        // 유저 생성과 함께 장바구니 생성 후 연결
        Cart cart = Cart.builder()
                .user(user)
                .build();
        user.setCart(cart);

        return userRepository.save(user).getId();
    }
}
