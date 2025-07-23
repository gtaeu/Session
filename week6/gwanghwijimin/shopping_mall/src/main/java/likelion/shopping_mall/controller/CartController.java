package likelion.shopping_mall.controller;

import likelion.shopping_mall.dto.UserDTO;
import likelion.shopping_mall.dto.request.AddToCartRequest;
import likelion.shopping_mall.dto.response.AddToCartResponse;
import likelion.shopping_mall.dto.response.CartResponse;
import likelion.shopping_mall.dto.response.CommonResponse;
import likelion.shopping_mall.entity.User;
import likelion.shopping_mall.repository.UserRepository;
import likelion.shopping_mall.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carts")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final UserRepository userRepository;

    @GetMapping
    public ResponseEntity<CartResponse> getCart(@AuthenticationPrincipal UserDTO userDTO) {
        User user = userRepository.findByEmail(userDTO.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        return ResponseEntity.ok(cartService.getCart(user.getId()));
    }

    @PostMapping
    public ResponseEntity<CommonResponse<AddToCartResponse>> addToCart(
            @RequestBody AddToCartRequest request,
            @AuthenticationPrincipal UserDTO userDTO) {

        User user = userRepository.findByEmail(userDTO.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        AddToCartResponse response = cartService.addItemToCart(user.getId(), request);
        return ResponseEntity.ok(CommonResponse.success(response));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<CommonResponse<Object>> removeFromCart(
            @PathVariable Long productId,
            @AuthenticationPrincipal UserDTO userDTO) {

        User user = userRepository.findByEmail(userDTO.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        cartService.removeItemFromCart(user.getId(), productId);
        return ResponseEntity.ok(CommonResponse.success(null, "장바구니에서 상품을 삭제했습니다."));
    }
}
