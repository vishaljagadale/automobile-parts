package com.autoparts.marketplace.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String sku;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "sub_category_id", nullable = false)
    private SubCategory subCategory;

    @ManyToOne
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;

    @ManyToOne
    @JoinColumn(name = "vehicle_type_id", nullable = false)
    private VehicleType vehicleType;

    @Column(nullable = false)
    private String model;

    @Column(name = "product_year", nullable = false)
    private int year;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private boolean approved = false;

    @Lob
    private String specifications; // JSON string

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<ProductImage> images;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    private Inventory inventory;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getSku() { return sku; }
    public String getName() { return name; }
    public Category getCategory() { return category; }
    public SubCategory getSubCategory() { return subCategory; }
    public Brand getBrand() { return brand; }
    public VehicleType getVehicleType() { return vehicleType; }
    public String getModel() { return model; }
    public int getYear() { return year; }
    public java.math.BigDecimal getPrice() { return price; }
    public String getSpecifications() { return specifications; }
    public boolean isApproved() { return approved; }
    public Inventory getInventory() { return inventory; }
    public Set<ProductImage> getImages() { return images; }
    public void setSku(String sku) { this.sku = sku; }
    public void setName(String name) { this.name = name; }
    public void setCategory(Category category) { this.category = category; }
    public void setSubCategory(SubCategory subCategory) { this.subCategory = subCategory; }
    public void setBrand(Brand brand) { this.brand = brand; }
    public void setVehicleType(VehicleType vehicleType) { this.vehicleType = vehicleType; }
    public void setModel(String model) { this.model = model; }
    public void setYear(int year) { this.year = year; }
    public void setPrice(java.math.BigDecimal price) { this.price = price; }
    public void setSpecifications(String specifications) { this.specifications = specifications; }
    public void setApproved(boolean approved) { this.approved = approved; }
    public void setInventory(Inventory inventory) { this.inventory = inventory; }
    public void setImages(Set<ProductImage> images) { this.images = images; }
}