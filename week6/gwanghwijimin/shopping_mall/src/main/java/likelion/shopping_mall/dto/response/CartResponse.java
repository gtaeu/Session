package likelion.shopping_mall.dto.response;


import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class CartResponse {
    private List<CartItemResponse> cartItems;
    private int cartTotalItems;
    private int cartTotalPrice;

    @Builder
    public CartResponse(List<CartItemResponse> cartItems) {
        this.cartItems = cartItems;
        this.cartTotalItems = cartItems.size();
        this.cartTotalPrice = cartItems.stream()
                .mapToInt(CartItemResponse::getTotalPrice)
                .sum();
    }
}
