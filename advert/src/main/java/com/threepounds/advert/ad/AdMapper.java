package com.threepounds.advert.ad;


import org.mapstruct.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface AdMapper {
  AdDto adToAdDto(Ad ad);
  Ad adDtoToAd(AdDto adDto);
  AdResource adListToAdResourceList(Ad ad);
  List<AdResource> adListToAdResourceList(List<Ad> ad);
}
