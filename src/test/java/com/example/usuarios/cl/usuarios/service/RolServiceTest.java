package com.example.usuarios.cl.usuarios.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.usuarios.cl.usuarios.model.Rol;
import com.example.usuarios.cl.usuarios.repository.RolRepository;

@SpringBootTest
public class RolServiceTest {

    @Autowired
    private RolService rolService;

    @MockBean
    private RolRepository rolRepository;

    @Test
    public void testFindAll() {
        Rol rol = new Rol(1L,"rol 1");
        when(rolRepository.findAll()).thenReturn(List.of(rol));
        List<Rol> roles = rolService.findAll();
        assertNotNull(roles);
        assertEquals(1, roles.size());
    }

    @Test
    public void testFindById() {
        Long id = 1L;
        Rol rol = new Rol(1L,"rol 1");
        when(rolRepository.findById(id)).thenReturn(Optional.of(rol));
        Rol encontrado = rolService.findById(id);
        assertNotNull(encontrado);
        assertEquals(id, encontrado.getId());
    }

    @Test
    public void testSave() {
        Rol rol = new Rol(1L,"rol 1");
        when(rolRepository.save(rol)).thenReturn(rol);
        assertNotNull(rol);
        assertEquals(1, rol.getId());
    }
    
    @Test
    public void testDeleteById() {
        Long id = 1L;
        doNothing().when(rolRepository).deleteById(id);
        rolService.deleteById(id);
        verify(rolRepository, times(1)).deleteById(id);
    }
}
