package org.usvm.spring.auth.benchmarks.service;

import org.springframework.stereotype.Service;
import org.usvm.spring.auth.benchmarks.models.PermissionType;

import java.util.HashSet;
import java.util.Set;

@Service
public class UtilsService {

    public boolean checkPerms(Object principal, Set<PermissionType> permissionTypes){
        var userPerms = getPerms(principal);
        if (userPerms.contains(PermissionType.ADMIN))
            return true;
        for (var p : permissionTypes){
            if (!userPerms.contains(p)) return false;
        }
        return true;
    }

    private Set<PermissionType> getPerms(Object principal) {
        return Set.of(PermissionType.CAN_READ);
    }
}
