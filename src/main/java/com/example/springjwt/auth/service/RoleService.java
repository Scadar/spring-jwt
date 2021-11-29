
package com.example.springjwt.auth.service;

import com.example.springjwt.auth.model.Role;
import com.example.springjwt.auth.model.RoleName;
import com.example.springjwt.auth.repository.RoleRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    /**
     * Find all roles from the database
     */
    public Collection<Role> findAll() {
        return roleRepository.findAll();
    }

    public Role findByRoleName(RoleName roleName) throws NotFoundException {
        return roleRepository
                .findByRole(roleName)
                .orElseThrow(() -> new NotFoundException("Role " + roleName.name() + " not found"));
    }
}
