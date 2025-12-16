package com.example.usuarios.cl.usuarios.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.usuarios.cl.usuarios.model.Rol;
import com.example.usuarios.cl.usuarios.repository.RolRepository;

@ExtendWith(MockitoExtension.class)
class RolServiceTest {

    @InjectMocks
    private RolService rolService;

    @Mock
    private RolRepository rolRepository;

    @Test
    void testFindAll() {
        Rol rol = new Rol(1L, "ROL_1");
        when(rolRepository.findAll()).thenReturn(List.of(rol));

        List<Rol> roles = rolService.findAll();

        assertNotNull(roles);
        assertEquals(1, roles.size());
    }

    @Test
    void testFindById() {
        Long id = 1L;
        Rol rol = new Rol(id, "ROL_1");
        when(rolRepository.findById(id)).thenReturn(Optional.of(rol));

        Rol encontrado = rolService.findById(id);

        assertNotNull(encontrado);
        assertEquals(id, encontrado.getId());
    }

    @Test
    void testSave() {
        Rol rol = new Rol(1L, "ROL_1");
        when(rolRepository.save(any(Rol.class))).thenReturn(rol);

        Rol guardado = rolService.save(rol);

        assertNotNull(guardado);
    }

    @Test
    void testDeleteById() {
        Long id = 1L;

        doNothing().when(rolRepository).deleteById(id);

        rolService.deleteById(id);

        verify(rolRepository, times(1)).deleteById(id);
    }
}

