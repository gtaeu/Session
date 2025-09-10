package week6.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import week6.dto.requestDto.ProductRequestDto;
import week6.dto.responseDto.ProductResponseDto;
import week6.dto.responseDto.ProductSummaryResponseDto;
import week6.entity.Product;
import week6.repository.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    /*
    1. 상품 등록
    2. 상품 개별 조회
    3. 상품 전체 조회
    4. 상품명으로 조회
     */
    private final ProductRepository productRepository;

    //item 생성
    @Transactional
    public Long createProduct(@RequestBody ProductRequestDto productRequestDto) {
        Product product = Product.builder()
                .productName(productRequestDto.getProductName())
                .category(productRequestDto.getCategory())
                .description(productRequestDto.getDescription())
                .price(productRequestDto.getPrice())
                .build();
        return productRepository.save(product).getProductId();
    }

    //상품 개별 조회
    public ProductResponseDto getProduct(Long productId) {
    Product product = productRepository.findById(productId)
            .orElseThrow(()-> new IllegalArgumentException("Product not found"));

    return ProductResponseDto.builder()
            .productId(product.getProductId())
            .productName(product.getProductName())
            .category(product.getCategory())
            .price(product.getPrice())
            .description(product.getDescription())
            .build();
    }

    //상품 전체 조회
    public List<ProductSummaryResponseDto> getAllProducts() {
        return productRepository.findAll().stream()
                .map(product -> ProductSummaryResponseDto.builder()
                        .productId(product.getProductId())
                        .productName(product.getProductName())
                        .category(product.getCategory())
                        .price(product.getPrice())
                        .build())
                .collect(Collectors.toList());
    }

    //상품명으로 조회
    public List<ProductSummaryResponseDto> getProductByNameContaining(String name) {
        return productRepository.findByProductNameContaining(name);
    }
}
