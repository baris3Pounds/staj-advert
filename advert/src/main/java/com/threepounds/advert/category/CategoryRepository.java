package com.threepounds.advert.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface CategoryRepository extends PagingAndSortingRepository<Category , UUID> {
}
