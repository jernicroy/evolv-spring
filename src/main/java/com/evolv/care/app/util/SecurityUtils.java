package com.evolv.care.app.util;


import com.evolv.care.app.security.EvolvUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {

    public static Long getAuthenticatedUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof EvolvUserDetails userDetails) {
            return userDetails.getUser().getId();
        }
        return null;
    }
}
