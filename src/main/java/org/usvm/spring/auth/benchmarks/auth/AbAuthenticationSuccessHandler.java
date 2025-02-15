package org.usvm.spring.auth.benchmarks.auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import java.io.IOException;
import java.security.Principal;

public class AbAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    private void onAuthSuccess(Object p) {
        System.out.println(p);
    }
    
    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException {
        var principal = authentication.getPrincipal();
        onAuthSuccess(principal);
        super.clearAuthenticationAttributes(request);
        response.setHeader("Auth", "Success");
    }

}
