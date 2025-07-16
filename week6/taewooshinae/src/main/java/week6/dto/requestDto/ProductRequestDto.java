package week6.dto.requestDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductRequestDto {
    private Long productId;
    private String productName;
    private String category;
    private int price;
    private String description;
}
