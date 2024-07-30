package com.threepounds.advert.category;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.threepounds.advert.country.Country;
import com.threepounds.advert.country.CountryDTO;
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
    public ResponseEntity<List<CategoryDto>> getCategories(@RequestParam Optional<Integer> no , @RequestParam Optional<Integer> size){

        List<Category> categories = categoryService.findAll(PageRequest.of(no.orElse(0), size.orElse(10)));
        List<CategoryDto> categoryDtoList = categoryMapper.categoryToCategoryDTO(categories);
        return ResponseEntity.ok().body(categoryDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(UUID id){
        Category category = categoryService.findById(id);
        CategoryDto categoryDto = categoryMapper.categoryToCategoryDTO(category);
        return ResponseEntity.ok().body(categoryDto);
    }

    @PostMapping("")
    public ResponseEntity createCategory(@RequestBody CategoryDto categoryDto){
        Category category = categoryMapper.categoryDTOToCategory(categoryDto);
        Category savedCategory = categoryService.save(category);
        return ResponseEntity.ok().body(savedCategory);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateCategory(@RequestBody CategoryDto categoryDto , @PathVariable UUID id){

        Category category = categoryMapper.categoryDTOToCategory(categoryDto);
        category.setId(id);
        Category savedCategory = categoryService.save(category);
        return ResponseEntity.ok().body(savedCategory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCategory(UUID id) {
        Category existingCategory = categoryService.findById(id);
        categoryService.deleteById(existingCategory);
        return ResponseEntity.ok().build();
    }

}
