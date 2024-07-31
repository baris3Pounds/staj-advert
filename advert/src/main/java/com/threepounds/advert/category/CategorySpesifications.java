package com.threepounds.advert.category;

import org.springframework.data.jpa.domain.Specification;

public class CategorySpesifications {
    public static Specification<Category> hasName(String name){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("name") , name));
    }

    public static Specification<Category> isActive(Boolean active){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("isActive") , active));
    }
}
