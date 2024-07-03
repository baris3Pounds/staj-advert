package com.threepounds.advert;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    List<User> findByName(String name);
    List<User> findByNameAndAge(String name, int age);
}
