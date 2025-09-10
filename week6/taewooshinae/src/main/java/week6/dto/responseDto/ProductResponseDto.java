package week6.dto.responseDto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class ProductResponseDto {
    private Long productId;
    private String productName;
    private String category;
    private int price;
    private String description;
}
