package likelion.shopping_mall.service;


import likelion.shopping_mall.dto.request.AddToCartRequest;
import likelion.shopping_mall.dto.response.AddToCartResponse;
import likelion.shopping_mall.dto.response.CartItemResponse;
import likelion.shopping_mall.dto.response.CartResponse;
import likelion.shopping_mall.dto.response.ProductResponseDto;
import likelion.shopping_mall.entity.Cart;
import likelion.shopping_mall.entity.CartItem;
import likelion.shopping_mall.entity.Product;
import likelion.shopping_mall.repository.CartItemRepository;
import likelion.shopping_mall.repository.CartRepository;
import likelion.shopping_mall.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final ProductService productService;

    // 장바구니 조회
    @Transactional(readOnly = true)
    public CartResponse getCart(Long userId) {
        Cart cart = cartRepository.findCartByUserId(userId)
                .orElseThrow(()->new IllegalArgumentException("장바구니가 존재하지 않습니다."));

        List<CartItemResponse> cartItems = cart.getCartItems().stream()
                .map(item -> CartItemResponse.builder()
                        .id(item.getProduct().getId())
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


    // 상품 추가
    public AddToCartResponse addItemToCart(Long userId, AddToCartRequest request) {

        // 상품 조회
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다."));

        // 장바구니 조회
        Cart cart = cartRepository.findCartByUserId(userId)
                .orElseThrow(()->new IllegalArgumentException("장바구니가 존재하지 않습니다."));

        // 장바구니에 있는 상품인지 확인 (있다면 수량 업데이트 없다면 추가)
        CartItem existingCartItem = cartItemRepository
                .findCartItemByProductIdAndCartId(request.getProductId(), cart.getId())
                .orElse(null);

        if (existingCartItem == null) {
            CartItem cartItem = CartItem.builder()
                    .product(product)
                    .cart(cart)
                    .quantity(request.getQuantity())
                    .build();

            cartItemRepository.save(cartItem);
        }
        else {
            existingCartItem.setQuantity(existingCartItem.getQuantity() + request.getQuantity());
            cartItemRepository.save(existingCartItem);
        }

        return new AddToCartResponse(request.getProductId(), LocalDateTime.now().toString());

    }

    // 상품 삭제
    public void removeItemFromCart(Long userId, Long productId) {
        Cart cart = cartRepository.findCartByUserId(userId)
                .orElseThrow(()->new IllegalArgumentException("장바구니가 존재하지 않습니다."));

        CartItem cartItem = cartItemRepository.findCartItemByProductIdAndCartId(productId, cart.getId())
                .orElseThrow(() -> new IllegalArgumentException("장바구니에 해당 상품이 없습니다."));

        cartItemRepository.delete(cartItem);
    }
}
