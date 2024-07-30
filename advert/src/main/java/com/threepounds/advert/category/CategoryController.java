package com.threepounds.advert.category;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.threepounds.advert.exception.GeneralResponse;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    private final CategoryMapper categoryMapper;

    public CategoryController(CategoryService categoryService, CategoryMapper categoryMapper) {
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
    }

    @GetMapping("")
    public ResponseEntity<GeneralResponse<Object>> getCategories(@RequestParam Optional<Integer> no , @RequestParam Optional<Integer> size){

        List<Category> categories = categoryService.findAll(PageRequest.of(no.orElse(0), size.orElse(10)));
        List<CategoryDto> adResourceList = categoryMapper.categoryToCategoryDTO(categories);
        return ResponseEntity.ok().body(GeneralResponse.<Object>builder().data(adResourceList).build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GeneralResponse<Object>> getCategoryById(UUID id){
        Category category = categoryService.findById(id);
        CategoryResource categoryResource = categoryMapper.categoryToCategoryResource(category);
        return ResponseEntity.ok().body(GeneralResponse.<Object>builder().data(categoryResource).build());
    }

    @PostMapping("")
    public ResponseEntity<GeneralResponse<Object>> createCategory(@RequestBody CategoryDto categoryDto){
        Category category = categoryMapper.categoryDTOToCategory(categoryDto);
        Category savedCategory = categoryService.save(category);
        CategoryResource categoryResource = categoryMapper.categoryToCategoryResource(savedCategory);
        return ResponseEntity.ok().body(GeneralResponse.<Object>builder().data(categoryResource).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<GeneralResponse<Object>> updateCategory(@RequestBody CategoryDto categoryDto , @PathVariable UUID id){

        Category category = categoryMapper.categoryDTOToCategory(categoryDto);
        categoryService.updateById(category , id);
        CategoryResource categoryResource = categoryMapper.categoryToCategoryResource(category);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GeneralResponse<Object>> deleteCategory(UUID id){
        Category existingCategory = categoryService.findById(id);
        categoryService.deleteById(existingCategory);
        return ResponseEntity.ok().build();
    }

}
