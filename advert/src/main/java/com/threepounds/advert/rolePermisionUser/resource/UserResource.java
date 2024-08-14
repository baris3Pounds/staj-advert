package com.threepounds.advert.rolePermisionUser.resource;

import com.threepounds.advert.rolePermisionUser.utils.enums.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserResource {
    private String name;
    private int age;
    private Gender gender;
    private boolean active;
    private List<UserResource> userResources = new ArrayList<>();
}
