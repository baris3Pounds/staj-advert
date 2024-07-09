package com.threepounds.advert.category;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {
    CategoryMapper instance = Mappers.getMapper(CategoryMapper.class);

    CategoryDto CategoryToCategoryDTO(Category category);
}
