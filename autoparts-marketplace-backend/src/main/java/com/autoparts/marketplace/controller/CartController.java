package com.autoparts.marketplace.controller;

import com.autoparts.marketplace.dto.CartDTO;
import com.autoparts.marketplace.dto.CartItemDTO;
import com.autoparts.marketplace.service.CartService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping("/{userId}")
    public ResponseEntity<CartDTO> getCart(@PathVariable Long userId) {
        CartDTO cart = cartService.getCartByUserId(userId);
        if (cart == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(cart);
    }

    @PostMapping("/add")
    public ResponseEntity<CartDTO> addToCart(@RequestBody @Valid CartDTO cartDTO) {
        CartDTO cart = null;
        if (cartDTO.getItems() != null) {
            for (CartItemDTO item : cartDTO.getItems()) {
                cart = cartService.addToCart(cartDTO.getUserId(), item.getProductId(), item.getQuantity());
            }
        }
        if (cart == null) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(cart);
    }

    @DeleteMapping("/item/{cartItemId}")
    public ResponseEntity<Void> removeItem(@PathVariable Long cartItemId) {
        cartService.removeItem(cartItemId);
        return ResponseEntity.noContent().build();
    }
}