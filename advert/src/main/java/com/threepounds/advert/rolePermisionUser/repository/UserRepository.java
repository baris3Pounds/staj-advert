package com.threepounds.advert.rolePermisionUser.repository;

import java.util.List;
import java.util.UUID;

import com.threepounds.advert.rolePermisionUser.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

  List<User> findByName(String name);

  List<User> findByNameAndAge(String name, int age);
}
