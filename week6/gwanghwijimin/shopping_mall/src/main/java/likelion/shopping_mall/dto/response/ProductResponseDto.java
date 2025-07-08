package likelion.shopping_mall.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductResponseDto {
    private Long id;
    private String name;
    private String category;
    private int price;
    private String description;
}
