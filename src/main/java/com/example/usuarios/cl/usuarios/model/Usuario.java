package com.example.usuarios.cl.usuarios.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuario")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Entidad que representa un usuario del sistema")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID unico del usuario", example = "10", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Column(nullable = false, length = 40)
    @Schema(description = "Nombre del usuario", example = "Juan Perez")
    private String nombre;

    @Column(nullable = false, unique = true, length = 40)
    @Schema(description = "Correo electronico del usuario", example = "juan.perez@gmail.com")
    private String correo;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(nullable = false, unique = true, length = 40)
    @Schema(description = "Contraseña del usuario (solo escritura)", example = "123456")
    private String contrasenia;

    @ManyToOne
    @JoinColumn(name = "id_rol")
    @Schema(description = "Rol asignado al usuario", example = """
            {
                "id": 1,
                "nombre": "ADMIN"
            }
            """)
    private Rol rol;
}
