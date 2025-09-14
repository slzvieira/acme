package com.slzvieira.acme.service;

import com.slzvieira.acme.model.Role;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    public Role findById(Long id) {
        return new Role(2L, "Camarero");
    }

    public List<Role> findAll() {
        return List.of(
                new Role(12L, "Musico"),
                new Role(23L, "Arquiteto"),
                new Role(52L, "Advogado"));
    }

    public Role create(Role role) {
        return role;
    }

    public Role update(Long id, Role role) {
        return role;
    }

    public Role delete(Long id) {
        return new Role(33L, "Medico");
    }
}
