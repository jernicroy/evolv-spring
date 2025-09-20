package com.evolv.care.app.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class AppConfig {

    @Value("${spring.app.redirect_url}")
    private String redirectUrl;

    @Value("${spring.app.allowed_paths}")
    private String allowedPaths;

}
