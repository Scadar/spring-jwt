
package com.example.springjwt.auth.repository;

import com.example.springjwt.auth.model.Role;
import com.example.springjwt.auth.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    public Optional<Role> findByRole(RoleName roleName);
}
