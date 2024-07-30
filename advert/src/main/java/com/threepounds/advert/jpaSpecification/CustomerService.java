package com.threepounds.advert.jpaSpecification;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public List<Customer> findCustomers(String name , int age){
        Specification<Customer> spec = Specification.where(CustomerSpesification.hasName(name) ).and(CustomerSpesification.hasOlderThan(age));

        return customerRepository.findAll(spec);
    }
}
