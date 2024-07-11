package com.threepounds.advert.category;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryDto categoryToCategoryDTO(Category category);

    Category categoryDTOToCategory(CategoryDto categoryDto);

    List<CategoryDto> categoryToCategoryDTO(List<Category> category);

    List<Category> categoryDTOToCategory(List<CategoryDto> categoryDto);

}
