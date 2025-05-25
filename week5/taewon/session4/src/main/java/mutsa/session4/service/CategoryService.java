package mutsa.session4.service;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import mutsa.session4.dto.CategoryRequestDto;
import mutsa.session4.dto.CategoryResponseDto;
import mutsa.session4.entity.Category;
import mutsa.session4.repository.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Long createCategory(CategoryRequestDto categoryRequestDto) {
        if(categoryRepository.findByName(categoryRequestDto.getName()).isPresent()){
            throw new IllegalStateException("중복 이름을 가진 카테고리가 존재합니다.");
        }
        if (categoryRequestDto.getParentId() == null) {
            Category initialValue = categoryRepository.findByName("initial_value").get();
            Category category = Category.builder()
                .name(categoryRequestDto.getName())
                .parent(initialValue)
                .hierarchy(1)
                .build();
            return categoryRepository.save(category).getId();
        } else {
            Category parent = categoryRepository.findById(categoryRequestDto.getParentId())
                .orElseThrow(() -> new IllegalStateException("해당 이름을 가진 부모 카테고리가 존재하지 않습니다."));
            Category category = Category.builder()
                .parent(parent)
                .name(categoryRequestDto.getName())
                .hierarchy(parent.getHierarchy() + 1)
                .build();
            return categoryRepository.save(category).getId();
        }
    }

    public CategoryResponseDto readCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
            .orElseThrow(() -> new IllegalStateException("해당 Id를 가진 카테고리가 존재하지 않습니다."));
        return CategoryResponseDto.of(category);
    }

    public CategoryResponseDto readRootCategory() {
        Category category = categoryRepository.findByName("initial_value")
            .orElseThrow(() -> new IllegalStateException("Root 노드가 존재하지 않습니다."));
        return CategoryResponseDto.of(category);
    }

}
