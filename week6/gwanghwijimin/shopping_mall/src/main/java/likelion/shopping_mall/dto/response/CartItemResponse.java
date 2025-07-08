package likelion.shopping_mall.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CartItemResponse {
    private Long id;    // cartItemId
    private String name;
    private String category;
    private int price;
    private int quantity;
    private int totalPrice;

    @Builder
    public CartItemResponse(Long id, String name, String category, int price, int quantity) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
        this.totalPrice = price * quantity;
    }
}
