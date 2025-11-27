package com.example.usuarios.cl.usuarios.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.usuarios.cl.usuarios.model.Rol;
import com.example.usuarios.cl.usuarios.service.RolService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/v1/roles")
@Tag(name = "Roles", description = "Operacion relacionada con los roles")
public class RolController {

    @Autowired
    private RolService rolService;

    @GetMapping
    @Operation(summary = "Obtener todos los roles", description = "Obtiene una lista de todos los roles")
    public ResponseEntity<List<Rol>> getAllRoles() {
        List<Rol> roles = rolService.findAll();
        if (roles.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(roles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rol> getRolById(@PathVariable Long id) {
        Rol rol = rolService.findById(id);
        if (rol == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(rol);
    }

    @PostMapping
    public ResponseEntity<Rol> createRol(@RequestBody Rol rol) {
        Rol createdRol = rolService.save(rol);
        return ResponseEntity.status(201).body(createdRol);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Rol> updateRol(@PathVariable Long id, @RequestBody Rol rol) {
        if (rolService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        rol.setId(id);
        Rol updatedRol = rolService.save(rol);
        return ResponseEntity.ok(updatedRol);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Rol> patchRol(@PathVariable Long id, @RequestBody Rol rol) {
        Rol patchedRol = rolService.patchRol(id, rol);
        if (patchedRol == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(patchedRol);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRol(@PathVariable Long id) {
        if (rolService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        rolService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
