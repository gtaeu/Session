package week6.dto.responseDto;

import lombok.Getter;
import lombok.Setter;
import week6.entity.Cart;
import week6.entity.CartItem;

import java.util.List;

@Getter
@Setter
public class CartResponse {
    private List<CartItemDto> cartItems;
    private int cartTotalItems;
    private int cartTotalPrice;

    public CartResponse(List<CartItemDto> cartItems) {
        this.cartItems = cartItems;
        this.cartTotalItems = cartItems.stream().mapToInt(CartItemDto::getQuantity).sum();
        this.cartTotalPrice = cartItems.stream().mapToInt(CartItemDto::getTotalPrice).sum();
    }
}
