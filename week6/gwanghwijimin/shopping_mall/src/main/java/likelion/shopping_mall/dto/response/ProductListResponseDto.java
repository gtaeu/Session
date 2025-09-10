package likelion.shopping_mall.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ProductListResponseDto {
    private List<ProductResponseDto> products; // 상품 리스트
    private int totalProducts;                // 총 상품 개수
}
