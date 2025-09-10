package likelion.shopping_mall.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AddToCartResponse {
    private Long productId;
    private String addedAt;
}
