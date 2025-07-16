package week6.dto.requestDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCartItemRequest {
    private Long productId;
    private Integer quantity;

}
