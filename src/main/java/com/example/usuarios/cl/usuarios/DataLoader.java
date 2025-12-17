package com.example.usuarios.cl.usuarios;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.usuarios.cl.usuarios.model.Rol;
import com.example.usuarios.cl.usuarios.model.Usuario;
import com.example.usuarios.cl.usuarios.repository.RolRepository;
import com.example.usuarios.cl.usuarios.repository.UsuarioRepository;

import net.datafaker.Faker;

@Component
@Transactional
public class DataLoader implements CommandLineRunner {

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void run(String... args) throws Exception {

        Faker faker = new Faker();
        Random random = new Random();

        if (rolRepository.count() == 0) {
            Rol admin = new Rol(null, "ADMIN");
            Rol user = new Rol(null, "USUARIO");

            rolRepository.save(admin);
            rolRepository.save(user);
        }

        List<Rol> roles = rolRepository.findAll();

        if (usuarioRepository.count() == 0) {
            for (int i = 0; i < 10; i++) {
                Usuario usuario = new Usuario();
                usuario.setNombre(faker.name().firstName());
                usuario.setCorreo(faker.internet().emailAddress());
                usuario.setContrasenia(faker.internet().password(8, 16));
                usuario.setRol(roles.get(random.nextInt(roles.size())));
                usuarioRepository.save(usuario);
            }
        }
    }
}
