package com.autoparts.marketplace.dto;

import java.math.BigDecimal;
import java.util.Set;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;

public class ProductDTO {
    private Long id;
    @NotBlank(message = "SKU is required")
    private String sku;
    @NotBlank(message = "Product name is required")
    @Size(min = 2, max = 100, message = "Product name must be between 2 and 100 characters")
    private String name;
    @NotNull(message = "Category is required")
    private Long categoryId;
    @NotNull(message = "Subcategory is required")
    private Long subCategoryId;
    @NotNull(message = "Brand is required")
    private Long brandId;
    @NotNull(message = "Vehicle type is required")
    private Long vehicleTypeId;
    @NotBlank(message = "Model is required")
    private String model;
    @Min(value = 1900, message = "Year must be valid")
    @Max(value = 2100, message = "Year must be valid")
    private int year;
    @NotNull(message = "Price is required")
    @Min(value = 1, message = "Price must be positive")
    private BigDecimal price;
    private boolean approved;
    private String specifications; // JSON string
    private Set<String> imageUrls;
    @Min(value = 1, message = "Quantity must be at least 1")
    private int quantity;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getSku() { return sku; }
    public void setSku(String sku) { this.sku = sku; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Long getCategoryId() { return categoryId; }
    public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }
    public Long getSubCategoryId() { return subCategoryId; }
    public void setSubCategoryId(Long subCategoryId) { this.subCategoryId = subCategoryId; }
    public Long getBrandId() { return brandId; }
    public void setBrandId(Long brandId) { this.brandId = brandId; }
    public Long getVehicleTypeId() { return vehicleTypeId; }
    public void setVehicleTypeId(Long vehicleTypeId) { this.vehicleTypeId = vehicleTypeId; }
    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }
    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public boolean isApproved() { return approved; }
    public void setApproved(boolean approved) { this.approved = approved; }
    public String getSpecifications() { return specifications; }
    public void setSpecifications(String specifications) { this.specifications = specifications; }
    public Set<String> getImageUrls() { return imageUrls; }
    public void setImageUrls(Set<String> imageUrls) { this.imageUrls = imageUrls; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}