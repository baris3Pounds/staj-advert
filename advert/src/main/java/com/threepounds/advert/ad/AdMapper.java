package com.threepounds.advert.ad;


import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AdMapper {

  AdDto adToAdDto(Ad ad);
  Ad adToAdDto(AdDto adDto);

}
