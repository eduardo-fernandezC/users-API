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
import com.example.usuarios.cl.usuarios.model.Usuario;
import com.example.usuarios.cl.usuarios.repository.UsuarioRepository;

@SpringBootTest
public class UsuarioServiceTest {

    @Autowired
    private UsuarioService usuarioService;

    @MockBean
    private UsuarioRepository usuarioRepository;
    
    @Test
    public void testFindAll() {
        Usuario usuario = new Usuario(1L, "Antonio", "toni@toñete.cl", "123abc", new Rol());
        when(usuarioRepository.findAll()).thenReturn(List.of(usuario));
        List<Usuario> usuarios = usuarioService.findAll();
        assertNotNull(usuarios);
        assertEquals(1, usuarios.size());
    }

    @Test
    public void testFindById() {
        Long id = 1L;
        Usuario usuario = new Usuario(1L, "Antonio", "toni@toñete.cl", "123abc", new Rol());
        when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuario));
        Usuario encontrado = usuarioService.findById(id);
        assertNotNull(encontrado);
        assertEquals(id, encontrado.getId());
    }

    @Test
    public void testSave() {
        Usuario usuario = new Usuario(1L, "Antonio", "toni@toñete.cl", "123abc", new Rol());
        when(usuarioRepository.save(usuario)).thenReturn(usuario);
        Usuario guardado = usuarioService.save(usuario);
        assertNotNull(guardado);
        assertEquals(1, guardado.getId());
    }

    @Test
    public void testDeleteById() {
        Long id = 1L;
        doNothing().when(usuarioRepository).deleteById(id);
        usuarioService.deleteById(id);
        verify(usuarioRepository, times(1)).deleteById(id);
    }
}
