package com.project.ecommerce.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "users")
public class UserEntity extends BaseEntity {

    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Size(min = 8)
    @Column(nullable = false)
    private String password;


}
