package com.autoparts.marketplace.service;

import com.autoparts.marketplace.dto.*;
import com.autoparts.marketplace.entity.*;
import com.autoparts.marketplace.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;

    public OrderDTO placeOrder(Long userId) {
        Cart cart = cartRepository.findByUserId(userId);
        if (cart == null || cart.getItems().isEmpty()) return null;
        Order order = new Order();
        order.setUser(cart.getUser());
        order.setStatus("PLACED");
        order.setOrderDate(LocalDateTime.now());
        Set<OrderItem> orderItems = new HashSet<>();
        for (CartItem cartItem : cart.getItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(cartItem.getProduct().getPrice());
            orderItems.add(orderItem);
        }
        order.setItems(orderItems);
        order = orderRepository.save(order);
        for (OrderItem item : orderItems) {
            orderItemRepository.save(item);
        }
        Payment payment = new Payment();
        payment.setOrder(order);
        payment.setStatus("PENDING");
        paymentRepository.save(payment);
        order.setPayment(payment);
        orderRepository.save(order);
        cart.getItems().clear();
        cartRepository.save(cart);
        return toDTO(order);
    }

    public Optional<OrderDTO> getOrderById(Long orderId) {
        return orderRepository.findById(orderId).map(this::toDTO);
    }

    public Set<OrderDTO> getOrdersByUserId(Long userId) {
        Set<OrderDTO> orders = orderRepository.findAll().stream()
            .filter(o -> o.getUser().getId().equals(userId))
            .map(this::toDTO)
            .collect(Collectors.toSet());
        return orders;
    }

    private OrderDTO toDTO(Order order) {
        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setUserId(order.getUser().getId());
        dto.setStatus(order.getStatus());
        dto.setOrderDate(order.getOrderDate());
        Set<OrderItemDTO> itemDTOs = order.getItems().stream().map(item -> {
            OrderItemDTO itemDTO = new OrderItemDTO();
            itemDTO.setId(item.getId());
            itemDTO.setProductId(item.getProduct().getId());
            itemDTO.setQuantity(item.getQuantity());
            itemDTO.setPrice(item.getPrice());
            return itemDTO;
        }).collect(Collectors.toSet());
        dto.setItems(itemDTOs);
        if (order.getPayment() != null) {
            PaymentDTO paymentDTO = new PaymentDTO();
            paymentDTO.setId(order.getPayment().getId());
            paymentDTO.setOrderId(order.getId());
            paymentDTO.setStatus(order.getPayment().getStatus());
            paymentDTO.setTransactionId(order.getPayment().getTransactionId());
            dto.setPayment(paymentDTO);
        }
        return dto;
    }
}
