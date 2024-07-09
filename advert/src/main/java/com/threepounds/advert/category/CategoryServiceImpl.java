package com.threepounds.advert.category;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper = CategoryMapper.INSTANCE;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ResponseEntity<Void> save(Category category) {
        categoryRepository.save(category);
        return ResponseEntity.ok().build();
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public ResponseEntity<CategoryDto> findById(UUID id) {
        Optional<Category> result = categoryRepository.findById(id);
        return result.map(category -> ResponseEntity.ok(categoryMapper.CategoryToCategoryDTO(category)))
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<Void> deleteById(UUID id) {
        Optional<Category> result = categoryRepository.findById(id);
        if(result.isPresent()) {
            categoryRepository.delete(result.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
