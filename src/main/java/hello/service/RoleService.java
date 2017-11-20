package hello.service;

import hello.model.Role;

import java.util.Set;

public interface RoleService {
    Set<Role> getMinimumAuthorities();

    Set<Role> getSuperAuthorities();

    Role findRoleByName(String roleName);

    Role findRoleById(Long id);
}
