package week6.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import week6.dto.requestDto.CreateCartItemRequest;
import week6.dto.responseDto.CartResponse;
import week6.dto.responseDto.CreateCartItemResponse;
import week6.dto.responseDto.DeleteCartItemResponse;
import week6.service.CartService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    @GetMapping
    public CartResponse findAllCartItems(){
        return cartService.getCart();
    }

    @PostMapping
    public CreateCartItemResponse createCartItem(@RequestBody CreateCartItemRequest req) {
        return cartService.createCartItem(req.getProductId(), req.getQuantity());
    }

    @DeleteMapping("/{id}")
    public DeleteCartItemResponse deleteCartItem(@PathVariable Long id) {
        return cartService.deleteCartItem(id);
    }
}
