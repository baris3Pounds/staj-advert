package com.threepounds.advert.category;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryDto categoryToCategoryDTO(Category category);


    Category categoryDTOToCategory(CategoryDto categoryDto);

    List<CategoryDto> categoryToCategoryDTO(List<Category> category);

    List<Category> categoryDTOToCategory(List<CategoryDto> categoryDto);

}
