package com.slzvieira.acme.controller;

import com.slzvieira.acme.controller.model.Role;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/roles")
@Tag(name = "Roles", description = "Operations related to roles")
public class RoleController {

    @Operation(summary = "Get a specific role by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful response"),
            @ApiResponse(responseCode = "404", description = "Role not found")})
    @GetMapping("/{id}")
    public ResponseEntity<Role> findRoleById(@PathVariable @Parameter(description = "Role code") Long id) {
        return ResponseEntity.ok(new Role(1L, "Padeiro"));
    }

    @Operation(summary = "Get the list of all registered roles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful response"),
            @ApiResponse(responseCode = "404", description = "No roles found")})
    @GetMapping
    public ResponseEntity<List<Role>> findAllRoles() {
        return ResponseEntity.ok(List.of(new Role(1L, "Padeiro"), new Role(2L, "Quitandeiro"), new Role(3L, "Confeiteiro")));
    }

    @Operation(summary = "Create a new role")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Role created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid data or required data missing")})
    @PostMapping
    public ResponseEntity<Role> createRole(@RequestBody Role role) {
        return ResponseEntity.ok(role);
    }

    @Operation(summary = "Update an existing role")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Role updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid data or required data missing"),
            @ApiResponse(responseCode = "404", description = "Role not found")})
    @PutMapping("/{id}")
    public ResponseEntity<Role> updateRole(@PathVariable @Parameter(description = "Role code") Long id, @RequestBody Role role) {
        return ResponseEntity.ok(role);
    }

    @Operation(summary = "Delete a role by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Role deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Role not found"),
            @ApiResponse(responseCode = "405", description = "Role cannot be deleted")})
    @DeleteMapping("/{id}")
    public ResponseEntity<Role> deleteRole(@PathVariable @Parameter(description = "Role code") Long id) {
        return ResponseEntity.ok(new Role(1L, "Padeiro"));
    }
}
