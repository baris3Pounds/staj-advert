package com.threepounds.advert.category;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
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
    public List<Category> findAll(PageRequest pageble) {
       Page<Category> page = categoryRepository.findAll(pageble);
        return page.toList();
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
        category.setId(id);
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> search(CategorySearchModel categorySearchModel) {
        Specification<Category> spec = Specification.where(CategorySpesifications
                .hasName(categorySearchModel.name()))
                .and(CategorySpesifications.isActive(categorySearchModel.isActive()));

        return categoryRepository.findAll(spec);
    }
}
