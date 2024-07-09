package com.threepounds.advert.category;

import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface CategoryService {
    public ResponseEntity save(Category category);

    public List<Category> findAll();

    public Category findById();

    public void deleteById();
}
