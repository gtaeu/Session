package week6.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import week6.dto.responseDto.CartItemDto;
import week6.dto.responseDto.CartResponse;
import week6.dto.responseDto.CreateCartItemResponse;
import week6.dto.responseDto.DeleteCartItemResponse;
import week6.entity.Cart;
import week6.entity.CartItem;
import week6.entity.Product;
import week6.repository.CartItemRepository;
import week6.repository.CartRepository;
import week6.repository.ProductRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;

    public List<CartItem> getCartItems() {
        return cartItemRepository.findAll();
    }

    public CreateCartItemResponse createCartItem(Long productId, Integer quantity) {
        Product product = productRepository.findById(productId).orElse(null);
        if (product == null) {
            return new CreateCartItemResponse(false);
        }

        Cart cart = cartRepository.findById(1L)
                .orElseGet(() -> cartRepository.save(new Cart()));

        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);

        CartItem saved = cartItemRepository.save(cartItem);


        return new CreateCartItemResponse(
                true,
                saved.getProduct().getProductId(),
                LocalDateTime.now()
        );
    }

    public CartResponse getCart() {
        List<CartItem> cartItems = cartItemRepository.findAll();

        List<CartItemDto> itemDtos = cartItems.stream()
                .map(item -> {
                    Product p = item.getProduct();
                    return new CartItemDto(
                            p.getProductId(),
                            p.getProductName(),
                            p.getCategory(),
                            p.getPrice(),
                            item.getQuantity()
                    );
                })
                .collect(Collectors.toList());
        return new CartResponse(itemDtos);
    }

    public DeleteCartItemResponse deleteCartItem(Long id) {
        if (!cartItemRepository.existsById(id)) {
            return new DeleteCartItemResponse(false, "아이템이 존재하지 않습니다");
        }

        cartItemRepository.deleteById(id);
         return new DeleteCartItemResponse(true, "장바구니 항목이 삭제되었습니다");

    }
}
