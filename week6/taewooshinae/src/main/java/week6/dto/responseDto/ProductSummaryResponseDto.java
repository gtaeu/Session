package week6.dto.responseDto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductSummaryResponseDto {
    private Long productId;
    private String category;
    private String productName;
    private int price;
}
