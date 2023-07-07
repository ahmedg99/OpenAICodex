package tn.spring.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import tn.spring.springboot.entities.Role;

@EnableJpaRepositories
public interface RoleRepository extends JpaRepository<Role,Integer> {
    Role findRoleByRoleName(String roleName);
}
