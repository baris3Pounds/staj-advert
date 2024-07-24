package com.threepounds.advert.category;


import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
    private UUID id;

   @NotBlank(message = "The name can't be blank")
    private String name;

   @NotNull(message = "the active value should be boolean , not null")
    private boolean active;
}
