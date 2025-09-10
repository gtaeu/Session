package mutsa.session4.controller;

import lombok.RequiredArgsConstructor;
import mutsa.session4.dto.CategoryRequestDto;
import mutsa.session4.dto.CategoryResponseDto;
import mutsa.session4.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/categories")
    public ResponseEntity<Long> createCategory(@RequestBody CategoryRequestDto categoryRequestDto) {
        return ResponseEntity.status(200).body(categoryService.createCategory(categoryRequestDto));
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<CategoryResponseDto> readCategory(@PathVariable Long id) {
        return ResponseEntity.status(200).body(categoryService.readCategory(id));
    }

    @GetMapping("/categories") //트리 구조에서 루트 노드를 조회 함으로써 전체 카테고리를 조회한다.
    public ResponseEntity<CategoryResponseDto> readAllCategories(@RequestBody CategoryRequestDto categoryRequestDto) {
        return ResponseEntity.status(200).body(categoryService.readRootCategory());
    }

}
