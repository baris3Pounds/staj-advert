package com.threepounds.advert.RestTemplateTrain;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "Spring")
public interface LocationMapper {

    @Mapping(source = "as" , target = "asDescription")
    Location LocationDtoToLocation(LocationDto locationDto);

}
