package com.example.rgfshop.repository;

import com.example.rgfshop.model.Order;
import com.example.rgfshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser(User user);
}
