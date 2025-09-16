package com.slzvieira.acme.service;

import com.slzvieira.acme.exception.AlreadyExistsException;
import com.slzvieira.acme.exception.InvalidDataException;
import com.slzvieira.acme.exception.NotFoundException;
import com.slzvieira.acme.model.Role;
import com.slzvieira.acme.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

import static java.util.Objects.nonNull;

@RequiredArgsConstructor
@Service
public class RoleService {

    private final RoleRepository repository;

    public Role findById(Long id) {
        return repository.findById(id).orElseThrow(notFoundException(id));
    }

    public List<Role> findAll() {

        List<Role> list = repository.findAll();

        if (list.isEmpty()) {
            throw new NotFoundException("Roles not found");
        }

        return repository.findAll();
    }

    public Role create(Role role) {

        if (repository.existsByName(role.getName())) {
            throw new AlreadyExistsException(String.format("Role '%s' already exists", role.getName()));
        }

        role.setId(null);
        return repository.save(role);
    }

    public Role update(Long id, Role role) {

        if (nonNull(role.getId()) && !Objects.equals(id, role.getId())) {
            throw new InvalidDataException("Role id must be the same");
        }

        if (!repository.existsById(id)) {
            throw notFoundException(id).get();
        }

        return repository.save(role);
    }

    public Role delete(Long id) {
        Role role = repository.findById(id).orElseThrow(notFoundException(id));
        repository.deleteById(id);
        return role;
    }

    private Supplier<NotFoundException> notFoundException(Long id) {
        return () -> new NotFoundException(String.format("Role '%s' not found", id));
    }
}
