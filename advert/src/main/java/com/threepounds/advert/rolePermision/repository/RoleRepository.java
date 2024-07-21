package com.threepounds.advert.rolePermision.repository;

import com.threepounds.advert.rolePermision.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {
}
