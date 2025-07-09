package likelion.shopping_mall.controller;

import likelion.shopping_mall.dto.request.AddToCartRequest;
import likelion.shopping_mall.dto.response.AddToCartResponse;
import likelion.shopping_mall.dto.response.CommonResponse;
import likelion.shopping_mall.dto.response.CartResponse;
import likelion.shopping_mall.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/carts")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping
    public ResponseEntity<CartResponse> getCartByUserId(@RequestParam Long userId) {
        // 로그인 인증 로직 없어서 일단 Request Param 으로 유저 아이디 넘겨서 했어용
        return ResponseEntity.ok(cartService.getCart(userId));
    }


    @PostMapping
    public ResponseEntity<CommonResponse<AddToCartResponse>> addToCart(@RequestBody AddToCartRequest request, @RequestParam Long userId) {
        AddToCartResponse response = cartService.addItemToCart(userId, request);
        return ResponseEntity.ok(CommonResponse.success(response));
    }


    @DeleteMapping("/{productId}")
    public ResponseEntity<CommonResponse<Object>> removeFromCart(@PathVariable Long productId, @RequestParam Long userId) {
        cartService.removeItemFromCart(productId, userId);
        return ResponseEntity.ok(CommonResponse.success(null, "장바구니에 해당 상품을 삭제했습니다."));
    }

}
