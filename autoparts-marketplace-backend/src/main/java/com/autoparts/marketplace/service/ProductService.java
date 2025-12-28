package com.autoparts.marketplace.service;

import com.autoparts.marketplace.dto.ProductDTO;
import com.autoparts.marketplace.entity.*;
import com.autoparts.marketplace.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private SubCategoryRepository subCategoryRepository;
    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private VehicleTypeRepository vehicleTypeRepository;
    @Autowired
    private InventoryRepository inventoryRepository;
    @Autowired
    private ProductImageRepository productImageRepository;

    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public Optional<ProductDTO> getProductById(Long id) {
        return productRepository.findById(id).map(this::toDTO);
    }

    public ProductDTO addProduct(ProductDTO dto) {
        if (dto.getCategoryId() == null || dto.getSubCategoryId() == null ||
            dto.getBrandId() == null || dto.getVehicleTypeId() == null) {
            throw new IllegalArgumentException("Category, SubCategory, Brand, and VehicleType IDs must not be null");
        }
        Product product = new Product();
        product.setSku(dto.getSku());
        product.setName(dto.getName());
        product.setCategory(categoryRepository.findById(dto.getCategoryId()).orElse(null));
        product.setSubCategory(subCategoryRepository.findById(dto.getSubCategoryId()).orElse(null));
        product.setBrand(brandRepository.findById(dto.getBrandId()).orElse(null));
        product.setVehicleType(vehicleTypeRepository.findById(dto.getVehicleTypeId()).orElse(null));
        product.setModel(dto.getModel());
        product.setYear(dto.getYear());
        product.setPrice(dto.getPrice());
        product.setSpecifications(dto.getSpecifications());
        product.setApproved(dto.isApproved());
        Product saved = productRepository.save(product);
        Inventory inventory = new Inventory();
        inventory.setProduct(saved);
        inventory.setQuantity(dto.getQuantity());
        inventoryRepository.save(inventory);
        return toDTO(saved);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public Page<ProductDTO> searchProducts(
        String vehicleType, String brand, String model, Integer year,
        BigDecimal minPrice, BigDecimal maxPrice, Boolean available,
        Pageable pageable
    ) {
        Specification<Product> spec = Specification.where(null);
        if (vehicleType != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("vehicleType").get("name"), vehicleType));
        }
        if (brand != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("brand").get("name"), brand));
        }
        if (model != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("model"), model));
        }
        if (year != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("year"), year));
        }
        if (minPrice != null) {
            spec = spec.and((root, query, cb) -> cb.ge(root.get("price"), minPrice));
        }
        if (maxPrice != null) {
            spec = spec.and((root, query, cb) -> cb.le(root.get("price"), maxPrice));
        }
        if (available != null) {
            spec = spec.and((root, query, cb) -> cb.greaterThan(root.get("inventory").get("quantity"), 0));
        }
        Page<Product> page = productRepository.findAll(spec, pageable);
        return page.map(this::toDTO);
    }

    private ProductDTO toDTO(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setSku(product.getSku());
        dto.setName(product.getName());
        dto.setCategoryId(product.getCategory().getId());
        dto.setSubCategoryId(product.getSubCategory().getId());
        dto.setBrandId(product.getBrand().getId());
        dto.setVehicleTypeId(product.getVehicleType().getId());
        dto.setModel(product.getModel());
        dto.setYear(product.getYear());
        dto.setPrice(product.getPrice());
        dto.setSpecifications(product.getSpecifications());
        dto.setApproved(product.isApproved());
        dto.setQuantity(product.getInventory() != null ? product.getInventory().getQuantity() : 0);
        dto.setImageUrls(product.getImages() != null ? product.getImages().stream().map(ProductImage::getImageUrl).collect(Collectors.toSet()) : null);
        return dto;
    }
}