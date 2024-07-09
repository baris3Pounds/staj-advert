package com.threepounds.advert.category;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/category")
    public List<Category> getCategories(){
        return categoryService.findAll();
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(UUID id){
       return categoryService.findById(id);
    }

    @PostMapping("/category")
    public ResponseEntity createCategory(@RequestBody Category category){
        return categoryService.save(category);
    }

    @PutMapping("/category/{id}")
    public ResponseEntity updateCategory(@RequestBody Category category , @PathVariable UUID id){
        categoryService.updateById(category , id);
    }

    @DeleteMapping("/category/{id}")
    public ResponseEntity deleteCategory(UUID id){
      return  categoryService.deleteById(id);
    }

}
