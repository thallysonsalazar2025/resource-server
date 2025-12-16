package com.example.resourceserver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebSecurity
public class ResourceServerConfiguration {
    private  AbstractHttpConfigurer abstractHttpConfigurer;


    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http.csrf((Customizer<ServerHttpSecurity.CsrfSpec>) abstractHttpConfigurer.disable())
                .authorizeExchange(authorize -> authorize
                        .pathMatchers("/public").permitAll()
                        .anyExchange().authenticated()
                )
                //todo configura o servidor para aceitar e validar tokens JWT (Bearer Token)
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(Customizer.withDefaults())
                );
        return http.build();
}
}
