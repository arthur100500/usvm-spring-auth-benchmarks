package org.usvm.spring.auth.benchmarks.models;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class RegisterUser {
    @Size(min = 6, max = 300, message = "UserName must be above 5 characters")
    @NotNull(message = "Username cannot be null")
    public String username;

    public String pwd;

    public String role;
}
