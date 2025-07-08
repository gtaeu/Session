package likelion.shopping_mall.controller;

import likelion.shopping_mall.dto.response.ProductListResponseDto;
import likelion.shopping_mall.dto.response.ProductResponseDto;
import likelion.shopping_mall.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    /**
     * 상품 전체 조회
     * (GET /products) 또는 (GET /products?name=샴푸)
     * name 파라미터가 없으면 전체 조회, 있으면 검색
     */
    @GetMapping
    public ResponseEntity<ProductListResponseDto> getProducts(@RequestParam(required = false) String name) {
        return ResponseEntity.ok(productService.getProducts(name));
    }

    /**
     * 상품 단일 조회
     * GET /products/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getProduct(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProduct(id));
    }
}
