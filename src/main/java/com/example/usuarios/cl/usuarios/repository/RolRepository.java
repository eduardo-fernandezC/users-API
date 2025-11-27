package com.example.usuarios.cl.usuarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.usuarios.cl.usuarios.model.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {

}
