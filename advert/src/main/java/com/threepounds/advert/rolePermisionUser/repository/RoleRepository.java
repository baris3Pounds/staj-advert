package com.threepounds.advert.rolePermisionUser.repository;

import com.threepounds.advert.rolePermisionUser.entity.Role;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {

  Optional<Role> findByCode(String code);
}
