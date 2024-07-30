package com.threepounds.advert.jpaSpecification;

import org.springframework.data.jpa.domain.Specification;

public class CustomerSpesification {
   public static Specification<Customer> hasName(String name){
       return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("name") , name));
   }

   public static Specification hasOlderThan(int age){
       return ((root, query, criteriaBuilder) -> criteriaBuilder.greaterThan(root.get("age").as(Integer.class) , age ));
   }
}
