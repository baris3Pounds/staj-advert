package com.threepounds.advert.rolePermision.repository;

import com.threepounds.advert.rolePermision.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PermissionRepository extends JpaRepository<Permission, UUID> {
}
