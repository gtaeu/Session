package week6.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import week6.dto.requestDto.ProductRequestDto;
import week6.dto.responseDto.ProductResponseDto;
import week6.dto.responseDto.ProductSummaryResponseDto;
import week6.entity.Product;
import week6.repository.ProductRepository;
import week6.service.ProductService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Long> createProduct(@RequestBody ProductRequestDto productRequestDto) {
        return ResponseEntity.status(200).body(productService.createProduct(productRequestDto));
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponseDto> getProduct(@PathVariable Long productId) {
        return ResponseEntity.status(200).body(productService.getProduct(productId));
    }

    @GetMapping("/list")
    public ResponseEntity<List<ProductSummaryResponseDto>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/list/{productName}")
    public ResponseEntity<List<ProductSummaryResponseDto>> getProductByName(@PathVariable String productName) {
        return ResponseEntity.ok(productService.getProductByNameContaining(productName));
    }
}
