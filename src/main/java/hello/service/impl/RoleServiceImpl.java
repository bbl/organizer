package hello.service.impl;

import hello.model.Role;
import hello.repository.RoleRepository;
import hello.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Set<Role> getMinimumAuthorities() {
        return new HashSet<>(roleRepository.findAllByNameIn(Arrays.asList("user")));
    }

    @Override
    public Set<Role> getSuperAuthorities() {
        return new HashSet<>(roleRepository.findAllByNameIn(Arrays.asList("admin")));
    }

    @Override
    public Role findRoleByName(String roleName) {
        return roleRepository.findByName(roleName);
    }

    @Override
    public Role findRoleById(Long id) {
        return roleRepository.findOne(id);
    }
}
