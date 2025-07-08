package com.example.rgfshop.controller;

import com.example.rgfshop.model.Order;
import com.example.rgfshop.model.User;
import com.example.rgfshop.repository.OrderRepository;
import com.example.rgfshop.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin
public class OrderController {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public OrderController(OrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    
    @PostMapping
    public String placeOrder(@RequestParam String username,
                             @RequestParam String product,
                             @RequestParam(defaultValue = "1") int quantity) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return "User not found";
        }

        Order order = new Order();
        order.setUser(user);
        order.setProduct(product);
        order.setQuantity(quantity);
        orderRepository.save(order);

        return "Order placed successfully!";
    }

    
    @GetMapping("/my")
    public ResponseEntity<List<Order>> getUserOrders(Principal principal) {
        if (principal == null) {
            return ResponseEntity.status(401).build(); // Unauthorized
        }

        User user = userRepository.findByUsername(principal.getName());
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        List<Order> orders = orderRepository.findByUser(user);
        return ResponseEntity.ok(orders);
    }

    
    @GetMapping("/all")
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    
    @GetMapping
    public List<Order> getOrdersByUsername(@RequestParam String username) {
        User user = userRepository.findByUsername(username);
        return (user != null) ? orderRepository.findByUser(user) : List.of();
    }
}
