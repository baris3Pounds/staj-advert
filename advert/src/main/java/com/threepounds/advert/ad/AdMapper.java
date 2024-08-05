package com.threepounds.advert.ad;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface AdMapper {
  AdDto adToAdDto(Ad ad);
  Ad adDtoToAd(AdDto adDto);
  AdResource adToAdResource(Ad ad);
  List<AdResource> adListToAdResourceList(List<Ad> ad);
}
