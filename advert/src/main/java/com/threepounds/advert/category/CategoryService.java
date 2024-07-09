package com.threepounds.advert.category;

import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;


public interface CategoryService {
    public ResponseEntity save(Category category);

    public List<Category> findAll();

    public ResponseEntity<CategoryDto> findById(UUID id);

    public ResponseEntity deleteById(UUID id);
}
