package com.threepounds.advert.rolePermisionUser.repository;

import com.threepounds.advert.rolePermisionUser.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PermissionRepository extends JpaRepository<Permission, UUID> {
}
