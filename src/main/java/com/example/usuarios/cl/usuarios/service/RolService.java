package com.example.usuarios.cl.usuarios.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.usuarios.cl.usuarios.model.Rol;
import com.example.usuarios.cl.usuarios.repository.RolRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RolService {

    @Autowired
    private RolRepository rolRepository;

    public List<Rol> findAll() {
        return rolRepository.findAll();
    }

    public Rol findById(Long id) {
        return rolRepository.findById(id).orElse(null);
    }

    public Rol save(Rol rol) {
        return rolRepository.save(rol);
    }

    public Rol patchRol(Long id, Rol rol) {
        Rol existeRol = findById(id);
        if (existeRol != null) {
            if (rol.getNombre() != null) {
                existeRol.setNombre(rol.getNombre());
            }
            return save(existeRol);
        }
        return null;
    }

    public void deleteById(Long id) {
        rolRepository.deleteById(id);
    }

}
