package com.example.usuarios.cl.usuarios.assemblers;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.example.usuarios.cl.usuarios.controller.RolControllerV2;
import com.example.usuarios.cl.usuarios.model.Rol;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;


@Component
public class RolModelAssembler implements RepresentationModelAssembler<Rol, EntityModel<Rol>>{

    @Override
    public EntityModel<Rol> toModel(Rol rol) {
        return EntityModel.of(rol,
                linkTo(methodOn(RolControllerV2.class).getRolById(rol.getId())).withSelfRel(),
                linkTo(methodOn(RolControllerV2.class).getAllRoles()).withRel("roles"));
    }
}
