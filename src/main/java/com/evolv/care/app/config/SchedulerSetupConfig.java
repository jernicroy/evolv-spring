package com.evolv.care.app.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class SchedulerSetupConfig {

    @Value("${spring.app.base.auth_token}")
    private String authToken;
}
