package com.project.ecommerce.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "categories")
@Data
public class CategoryEntity extends BaseEntity {

    @Column(name = "NAME")
    private String name;

    @Column(name = "SLUG")
    private String slug;

    @Column(name = "IS_ACTIVE")
    private boolean isActive;

    @Column(name = "PARENT_ID")
    private UUID parentId;

}
