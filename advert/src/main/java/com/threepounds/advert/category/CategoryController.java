package com.threepounds.advert.category;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.threepounds.advert.exception.GeneralResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    private final CategoryMapper categoryMapper;

    public CategoryController(CategoryService categoryService, CategoryMapper categoryMapper) {
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
    }


    @PreAuthorize("hasRole('ADMIN')")
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
    public ResponseEntity<GeneralResponse<Object>> createCategory(Authentication authentication, @RequestBody CategoryDto categoryDto){
        System.out.println("Principal ->" + authentication.getPrincipal());
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
    public ResponseEntity<GeneralResponse<Object>> deleteCategory(@PathVariable UUID id){
        log.info("");
        Category existingCategory = categoryService.findById(id);
        categoryService.deleteById(existingCategory);
        return ResponseEntity.ok().build();
    }

}
