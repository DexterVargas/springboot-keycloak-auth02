package com.dexterv.keycloak.config;

import com.dexterv.keycloak.config.KeycloakJwtRoleConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity(jsr250Enabled = true) // enables @RolesAllowed
public class SecurityConfig {

    private final KeycloakJwtRoleConverter roleConverter;

    public SecurityConfig(KeycloakJwtRoleConverter roleConverter) {
        this.roleConverter = roleConverter;
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                // No URL whitelists; everything needs a valid token.
                .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
                .oauth2ResourceServer(rs -> rs.jwt(jwt -> jwt
                        .jwtAuthenticationConverter(roleConverter.jwtAuthenticationConverter())));
        return http.build();
    }
}
