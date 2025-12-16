package com.example.usuarios.cl.usuarios.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
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
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.usuarios.cl.usuarios.model.Rol;
import com.example.usuarios.cl.usuarios.model.Usuario;
import com.example.usuarios.cl.usuarios.repository.UsuarioRepository;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @InjectMocks
    private UsuarioService usuarioService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    void testFindAll() {
        Usuario usuario = new Usuario(1L, "Antonio", "toni@tonete.cl", "123abc", new Rol());
        when(usuarioRepository.findAll()).thenReturn(List.of(usuario));

        List<Usuario> usuarios = usuarioService.findAll();

        assertNotNull(usuarios);
        assertEquals(1, usuarios.size());
    }

    @Test
    void testFindById() {
        Long id = 1L;
        Usuario usuario = new Usuario(1L, "Antonio", "toni@tonete.cl", "123abc", new Rol());
        when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuario));

        Usuario encontrado = usuarioService.findById(id);

        assertNotNull(encontrado);
        assertEquals(id, encontrado.getId());
    }

    @Test
    void testSave() {
        Usuario usuario = new Usuario(1L, "Antonio", "toni@tonete.cl", "123abc", new Rol());

        when(passwordEncoder.encode(anyString())).thenReturn("hashedPassword");
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        Usuario guardado = usuarioService.save(usuario);

        assertNotNull(guardado);
        verify(passwordEncoder, times(1)).encode(anyString());
        verify(usuarioRepository, times(1)).save(any(Usuario.class));
    }

    @Test
    void testDeleteById() {
        Long id = 1L;

        doNothing().when(usuarioRepository).deleteById(id);

        usuarioService.deleteById(id);

        verify(usuarioRepository, times(1)).deleteById(id);
    }
}
