package org.usvm.spring.auth.benchmarks.auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import java.io.IOException;

public class AbAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    
    private void onAuthFailure() {
        System.out.println("Auth failure");
    }
    
    @Override
    public void onAuthenticationFailure(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            AuthenticationException e)
            throws IOException {
        onAuthFailure();
        httpServletResponse.sendError(403, "Auth failure");
    }
}
