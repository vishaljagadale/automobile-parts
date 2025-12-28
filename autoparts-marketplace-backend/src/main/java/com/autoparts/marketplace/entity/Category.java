package com.autoparts.marketplace.entity;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "category")
    private Set<SubCategory> subCategories;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Set<SubCategory> getSubCategories() { return subCategories; }
    public void setSubCategories(Set<SubCategory> subCategories) { this.subCategories = subCategories; }
}
