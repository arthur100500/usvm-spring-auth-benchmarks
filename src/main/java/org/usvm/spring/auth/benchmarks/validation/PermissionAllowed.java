package org.usvm.spring.auth.benchmarks.validation;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.usvm.spring.auth.benchmarks.models.PermissionType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Payload;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@Inherited
@Order(Ordered.HIGHEST_PRECEDENCE)
public @interface PermissionAllowed {
    PermissionType[] permissionAllowed();

    String message() default "Not Authorized";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
