package org.usvm.spring.auth.benchmarks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;
import org.usvm.spring.auth.benchmarks.models.RegisterUser;

@Service
public class UserService {
    @Autowired(required = false)
    private InMemoryUserDetailsManager inMemoryUserDetailsManager;

    public void createNewUser(RegisterUser registerUser){
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        inMemoryUserDetailsManager.createUser(
                User.withUsername(registerUser.username)
                        .password(encoder.encode(registerUser.pwd))
                        .roles("USER")
                        .build());
    }
}
