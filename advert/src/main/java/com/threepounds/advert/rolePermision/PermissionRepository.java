package com.threepounds.advert.rolePermision;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PermissionRepository extends JpaRepository<Permission , UUID> {
}
