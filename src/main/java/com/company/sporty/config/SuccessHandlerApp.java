package com.company.sporty.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import java.io.IOException;

public class SuccessHandlerApp extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        boolean isAdmin = authentication.getAuthorities().stream().anyMatch(grant->grant.getAuthority().equals("ROLE_ADMIN"));
        if(isAdmin) {
            response.sendRedirect("/admin");
        }else {
            response.sendRedirect("/user");
        }
    }
}
