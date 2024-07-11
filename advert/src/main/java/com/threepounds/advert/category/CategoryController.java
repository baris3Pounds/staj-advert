package com.threepounds.advert.category;

import java.util.List;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class CategoryController {

    private final CategoryService categoryService;

    private final CategoryMapper categoryMapper;

    public CategoryController(CategoryService categoryService, CategoryMapper categoryMapper) {
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
    }

    @GetMapping("/category")
    public ResponseEntity<List<CategoryDto>> getCategories(){
        List<Category> categories = categoryService.findAll();
        List<CategoryDto> categoryDtoList = categoryMapper.categoryToCategoryDTO(categories);
        return ResponseEntity.ok().body(categoryDtoList);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(UUID id){
        Category category = categoryService.findById(id);
        CategoryDto categoryDto = categoryMapper.categoryToCategoryDTO(category);
        return ResponseEntity.ok().body(categoryDto);
    }

    @PostMapping("/category")
    public ResponseEntity createCategory(@RequestBody CategoryDto categoryDto){
        Category category = categoryMapper.categoryDTOToCategory(categoryDto);
        Category savedCategory = categoryService.save(category);
        return ResponseEntity.ok().body(savedCategory);
    }

    @PutMapping("/category/{id}")
    public ResponseEntity updateCategory(@RequestBody CategoryDto categoryDto , @PathVariable UUID id){

        Category category = categoryMapper.categoryDTOToCategory(categoryDto);
        categoryService.updateById(category , id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/category/{id}")
    public ResponseEntity deleteCategory(UUID id){
        Category existingCategory = categoryService.findById(id);
        categoryService.deleteById(existingCategory);
        return ResponseEntity.ok().build();
    }

}
