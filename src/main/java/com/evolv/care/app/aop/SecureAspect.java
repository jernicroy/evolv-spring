package com.evolv.care.app.aop;

import com.evolv.care.app.config.SchedulerSetupConfig;
import com.evolv.care.app.exception.ServerException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class SecureAspect {


    private final HttpServletRequest request;

    private final SchedulerSetupConfig schedulerSetupConfig;

    @Before("@annotation(com.evolv.care.app.aop.Secure)")
    public void secureEndpoint() {
        String strAuthorization = request.getHeader("Authorization");

        if (strAuthorization == null) {
            throw new ServerException("401", "Authorization header missing", HttpStatus.UNAUTHORIZED);
        }

        String strAuthToken = strAuthorization.startsWith("Bearer ")
                ? strAuthorization.substring(7)
                : strAuthorization;

        if (!strAuthToken.equals(schedulerSetupConfig.getAuthToken())) {
            throw new ServerException("401", "Invalid Authorization Token", HttpStatus.UNAUTHORIZED);
        }
    }
}
