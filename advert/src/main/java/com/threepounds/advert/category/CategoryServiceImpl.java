package com.threepounds.advert.category;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ResponseEntity save(Category category) {
        return null;
    }

    @Override
    public List<Category> findAll() {
        return List.of();
    }

    @Override
    public Category findById() {
        return null;
    }

    @Override
    public void deleteById() {

    }
}
