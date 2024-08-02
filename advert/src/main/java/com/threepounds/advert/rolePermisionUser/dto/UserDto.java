package com.threepounds.advert.rolePermisionUser.dto;

import com.threepounds.advert.rolePermisionUser.utils.enums.Gender;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    @NotBlank(message = "Not blank")
    private String name;

    @NotBlank(message = "Not blank")
    private String username;

    @Positive(message = "Age should be positive")
    private int age;

    @NotNull
    private Gender gender;

    @NotNull
    private boolean active;

    private String password;

}
