package com.threepounds.advert.category;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;


public interface CategoryService {
    public Category save(Category category);

    public List<Category> findAll(PageRequest pageble);

    public Category findById(UUID id) throws InterruptedException;

    public void deleteById(Category category);

    public Category updateById(Category category , UUID id);

    public List<Category> search(CategorySearchModel categorySearchModel);
}
