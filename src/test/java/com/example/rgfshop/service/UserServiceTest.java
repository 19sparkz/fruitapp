package com.example.rgfshop.service;

import com.example.rgfshop.model.User;
import com.example.rgfshop.repository.UserRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


public class UserServiceTest {

    private final UserRepository userRepository = mock(UserRepository.class);
    private final UserService userService = new UserService(userRepository);

    @Test
    void testRegisterNewUser() {
        when(userRepository.findByUsername("test")).thenReturn(null);
        when(userRepository.save(any())).thenAnswer(i -> i.getArgument(0));

        User user = userService.register("test", "pass123");
        assertNotNull(user);
        assertTrue(user.getPassword().startsWith("$2")); 
    }

    @Test
    void testLoginSuccess() {
        User user = new User();
        user.setUsername("john");
        user.setPassword(new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder().encode("secret"));
        when(userRepository.findByUsername("john")).thenReturn(user);

        assertTrue(userService.login("john", "secret"));
    }

    @Test
    void testLoginFailure() {
        when(userRepository.findByUsername("wrong")).thenReturn(null);
        assertFalse(userService.login("wrong", "nope"));
    }
}
