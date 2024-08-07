package com.threepounds.advert.ad;


import java.math.BigDecimal;
import org.springframework.data.jpa.domain.Specification;

public class AdSpecifications {

  public static Specification<Ad> isActive(){
    return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("active") , true));
  }

  public static Specification<Ad> hasTitle(String title){
    return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("title") , title));
  }

  public static Specification<Ad> priceGreaterThan(BigDecimal price){
    return ((root, query, criteriaBuilder) -> criteriaBuilder.greaterThan(root.get("price") , price));
  }

  public static Specification<Ad> hasLatitude(int latitude) {
    return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("latitude"), latitude);
  }

  public static Specification<Ad> hasLongitude(int longitude) {
    return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("longitude"), longitude);
  }


}
