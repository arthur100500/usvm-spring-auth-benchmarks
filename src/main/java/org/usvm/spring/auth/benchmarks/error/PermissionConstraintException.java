package org.usvm.spring.auth.benchmarks.error;

import jakarta.validation.ConstraintDeclarationException;

public class PermissionConstraintException extends ConstraintDeclarationException {

    public PermissionConstraintException(String msg) {
        super(msg);
    }
}
