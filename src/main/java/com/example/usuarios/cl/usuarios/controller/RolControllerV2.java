package com.example.usuarios.cl.usuarios.controller;

import com.example.usuarios.cl.usuarios.assemblers.RolModelAssembler;
import com.example.usuarios.cl.usuarios.model.Rol;
import com.example.usuarios.cl.usuarios.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
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
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/v2/roles")
public class RolControllerV2 {

    @Autowired
    private RolService rolService;

    @Autowired
    private RolModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Rol>> getAllRoles() {
        List<EntityModel<Rol>> roles = rolService.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(roles,
                linkTo(methodOn(RolControllerV2.class).getAllRoles()).withSelfRel());
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Rol> getRolById(@PathVariable Long id) {
        Rol rol = rolService.findById(id);
        return assembler.toModel(rol);
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Rol>> createRol(@RequestBody Rol rol) {
        Rol newRol = rolService.save(rol);
        return ResponseEntity
                .created(linkTo(methodOn(RolControllerV2.class).getRolById(newRol.getId())).toUri())
                .body(assembler.toModel(newRol));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Rol>> updateRol(@PathVariable Long id, @RequestBody Rol rol) {
        rol.setId(id);
        Rol updatedRol = rolService.save(rol);
        return ResponseEntity.ok(assembler.toModel(updatedRol));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> deleteRol(@PathVariable Long id) {
        rolService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
