package likelion.shopping_mall.dto.request;

import lombok.Getter;

@Getter
public class AddToCartRequest {
    private Long productId;
    private int quantity;
}
