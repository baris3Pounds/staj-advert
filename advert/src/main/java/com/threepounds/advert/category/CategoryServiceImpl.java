package com.threepounds.advert.category;

import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(UUID id) {
        return categoryRepository.findById(id).orElseThrow(()-> new RuntimeException("The category not found !"));
    }

    @Override
    public void deleteById(Category category) {
        categoryRepository.delete(category);
    }

    @Override
    public Category updateById(Category category, UUID id) {
        return categoryRepository.save(category);
    }
}
