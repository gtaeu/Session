package likelion.shopping_mall.service;


import likelion.shopping_mall.dto.response.CartItemResponse;
import likelion.shopping_mall.dto.response.CartResponse;
import likelion.shopping_mall.entity.Cart;
import likelion.shopping_mall.entity.CartItem;
import likelion.shopping_mall.repository.CartItemRepository;
import likelion.shopping_mall.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    // 장바구니 조회
    @Transactional(readOnly = true)
    public CartResponse getCart(Long userId) {
        Cart cart = cartRepository.findCartByUserId(userId);

        List<CartItemResponse> cartItems = cart.getCartItems().stream()
                .map(item -> CartItemResponse.builder()
                        .id(item.getId())
                        .name(item.getProduct().getName())
                        .category(item.getProduct().getCategory())
                        .price(item.getProduct().getPrice())
                        .quantity(item.getQuantity())
                        .build())
                .toList();

        return CartResponse.builder()
                .cartItems(cartItems)
                .build();
    }

    //TODO
    // - product 구현 후에 추가 예정
//    public AddToCartResponse addItemToCart(Long userId, AddToCartRequest request) {
//
//        Cart cart = cartRepository.findCartByUserId(userId);
//
//
//    }

    // 상품 삭제
    public void removeItemFromCart(Long userId, Long productId) {
        Cart cart = cartRepository.findCartByUserId(userId);
        CartItem cartItem = cartItemRepository.findCartItemByProductIdAndCartId(productId, cart.getId());
        cartItemRepository.delete(cartItem);
    }
}
