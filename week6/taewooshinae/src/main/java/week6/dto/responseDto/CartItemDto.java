package week6.dto.responseDto;

import lombok.Getter;

@Getter
public class CartItemDto {
    private Long id;
    private String name;
    private String category;
    private int price;
    private int quantity;
    private int totalPrice;

    public CartItemDto(Long id, String name, String category, int price, int quantity) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
        this.totalPrice = price * quantity;
    }
}
