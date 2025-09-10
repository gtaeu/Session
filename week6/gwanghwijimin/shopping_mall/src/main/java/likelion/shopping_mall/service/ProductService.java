package likelion.shopping_mall.service;

import likelion.shopping_mall.dto.response.ProductListResponseDto;
import likelion.shopping_mall.dto.response.ProductResponseDto;
import likelion.shopping_mall.entity.Product;
import likelion.shopping_mall.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    // 상품 전체 조회 (name이 null이면 전체 조회, 아니면 검색)
    public ProductListResponseDto getProducts(String name) {
        List<ProductResponseDto> list;

        if (name == null || name.isEmpty()) {
            // 전체 상품 조회
            list = productRepository.findAll()
                    .stream()
                    .map(this::toDto)
                    .collect(Collectors.toList());
        } else {
            // 이름 검색
            list = productRepository.findByNameContaining(name)
                    .stream()
                    .map(this::toDto)
                    .collect(Collectors.toList());
        }

        // ProductListResponseDto에 리스트와 총 개수 담아서 반환
        return new ProductListResponseDto(list, list.size());
    }

    // 단일 상품 조회
    public ProductResponseDto getProduct(Long id) {
        Product p = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다."));
        return toDto(p);
    }

    private ProductResponseDto toDto(Product p) {
        return new ProductResponseDto(
                p.getId(), p.getName(), p.getCategory(), p.getPrice(), p.getDescription()
        );
    }
}

