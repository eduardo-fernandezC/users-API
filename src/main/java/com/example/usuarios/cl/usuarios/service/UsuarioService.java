package com.example.usuarios.cl.usuarios.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.usuarios.cl.usuarios.model.Usuario;
import com.example.usuarios.cl.usuarios.repository.UsuarioRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Usuario findById(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario save(Usuario usuario) {

        if (usuario.getContrasenia() != null) {
            usuario.setContrasenia(
                    passwordEncoder.encode(usuario.getContrasenia()));
        }

        return usuarioRepository.save(usuario);
    }

    public Usuario patchUsuario(Long id, Usuario usuario) {
        Usuario existeUsuario = findById(id);
        if (existeUsuario != null) {
            if (usuario.getNombre() != null) {
                existeUsuario.setNombre(usuario.getNombre());
            }
            if (usuario.getCorreo() != null) {
                existeUsuario.setCorreo(usuario.getCorreo());
            }
            if (usuario.getContrasenia() != null) {
                existeUsuario.setContrasenia(usuario.getContrasenia());
            }
            if (usuario.getRol() != null) {
                existeUsuario.setRol(usuario.getRol());
            }
            return save(existeUsuario);
        }
        return null;
    }

    public void deleteById(Long id) {
        usuarioRepository.deleteById(id);
    }
}
