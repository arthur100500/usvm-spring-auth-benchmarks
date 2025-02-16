package org.usvm.spring.auth.benchmarks.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.usvm.spring.auth.benchmarks.error.PermissionConstraintException;
import org.usvm.spring.auth.benchmarks.models.PermissionType;
import org.usvm.spring.auth.benchmarks.service.UtilsService;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
@Component
public class PermissionAllowedAspectInterceptor {

    @Autowired
    private UtilsService utils;

    @Before("@annotation(permissionAllowed)")
    public void checkPermissions(PermissionAllowed permissionAllowed) throws Exception{
        // Check if you have PermissionAllowed validation annotations
        if (null == permissionAllowed)
            return;

        Set<PermissionType> allowedPermissions = new HashSet<>(Arrays.asList(permissionAllowed.permissionAllowed()));
        if (!utils.checkPerms(getPrincipal(), allowedPermissions)) {
            throw new PermissionConstraintException("Not Authorized");
        }
    }

    public static Object getPrincipal() {
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
