package com.autoparts.marketplace.service;

import com.autoparts.marketplace.dto.*;
import com.autoparts.marketplace.entity.*;
import com.autoparts.marketplace.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;

    public CartDTO getCartByUserId(Long userId) {
        Cart cart = cartRepository.findByUserId(userId);
        if (cart == null) return null;
        CartDTO dto = new CartDTO();
        dto.setId(cart.getId());
        dto.setUserId(cart.getUser().getId());
        Set<CartItemDTO> items = cart.getItems().stream().map(item -> {
            CartItemDTO itemDTO = new CartItemDTO();
            itemDTO.setId(item.getId());
            itemDTO.setProductId(item.getProduct().getId());
            itemDTO.setQuantity(item.getQuantity());
            return itemDTO;
        }).collect(Collectors.toSet());
        dto.setItems(items);
        return dto;
    }

    public CartDTO addToCart(Long userId, Long productId, int quantity) {
        Cart cart = cartRepository.findByUserId(userId);
        if (cart == null) {
            cart = new Cart();
            cart.setUser(userRepository.findById(userId).orElse(null));
            cart.setItems(new HashSet<>());
            cart = cartRepository.save(cart);
        }
        Optional<Product> productOpt = productRepository.findById(productId);
        if (productOpt.isEmpty()) return null;
        Product product = productOpt.get();
        CartItem item = new CartItem();
        item.setCart(cart);
        item.setProduct(product);
        item.setQuantity(quantity);
        cartItemRepository.save(item);
        cart.getItems().add(item);
        cartRepository.save(cart);
        return getCartByUserId(userId);
    }

    public void removeItem(Long cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }
}
