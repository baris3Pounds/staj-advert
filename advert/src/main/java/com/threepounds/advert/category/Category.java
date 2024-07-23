package com.threepounds.advert.category;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@Entity(name = "category")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Category {

    // id
    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    // name
    @Column(name = "name")
    private String name;

    // active
    @Column(name = "is_active")
    private boolean active;
    // new branch added
}
