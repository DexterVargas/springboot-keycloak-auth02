package com.dexterv.keycloak.controller;

import com.dexterv.keycloak.entity.User;
import com.dexterv.keycloak.service.UserService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    // Any authenticated user with role user or admin can list
    @RolesAllowed({"user", "admin"})
    @GetMapping
    public List<User> getAll(@AuthenticationPrincipal Jwt jwt) {
        System.out.println("Caller: " + jwt.getClaim("preferred_username"));
        return userService.findAll();
    }

    // Only admins can create
    @RolesAllowed("admin")
    @PostMapping
    public User create(@RequestBody User p) {
        return userService.save(p);
    }

    // Only admins can delete
    @RolesAllowed("admin")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }
}
