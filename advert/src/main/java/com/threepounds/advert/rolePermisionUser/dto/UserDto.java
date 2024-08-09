package com.threepounds.advert.rolePermisionUser.dto;

import com.threepounds.advert.rolePermisionUser.entity.Role;
import com.threepounds.advert.rolePermisionUser.utils.enums.Gender;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    private String name;

    @NotBlank(message = "Not blank")
    private String username;

    private int age;

    private Gender gender;

    private boolean active;

    private String password;

//    private List<Role> roles;

    private List<UUID> roles;

}
