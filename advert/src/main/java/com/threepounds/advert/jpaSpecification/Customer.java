package com.threepounds.advert.jpaSpecification;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class Customer {

    @Id
    @GeneratedValue
    private Long id;


    private String name;
}
