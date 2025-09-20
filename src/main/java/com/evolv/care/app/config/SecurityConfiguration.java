package com.evolv.care.app.config;

import com.evolv.care.app.security.EvolvUserDetailsService;
import com.evolv.care.app.security.filter.JwtAuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfiguration {

    private final JwtAuthFilter jwtAuthFilter;

    private final AppConfig appConfig;

    public SecurityConfiguration(EvolvUserDetailsService userDetailsService, JwtAuthFilter jwtAuthFilter, AppConfig appConfig) {
        this.jwtAuthFilter = jwtAuthFilter;
        this.appConfig = appConfig;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // store hashed passwords in your table
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http,
                                                       PasswordEncoder passwordEncoder,
                                                       UserDetailsService userDetailsService) throws Exception {
        AuthenticationManagerBuilder authBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);

        authBuilder
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);

        return authBuilder.build();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> {
                    try {
                        // Permit all OPTIONS requests
                        authorize.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll();

                        // Permit additional allowed paths from configuration
                        if (appConfig.getAllowedPaths() != null && !appConfig.getAllowedPaths().isEmpty()) {
                            String[] allowedPaths = appConfig.getAllowedPaths().split(",");
                            for (String path : allowedPaths) {
                                authorize.requestMatchers(path).permitAll();
                            }
                        }

                        // Require authentication for any other request
                        authorize.anyRequest().authenticated();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
