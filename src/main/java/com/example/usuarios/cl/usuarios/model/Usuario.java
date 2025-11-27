package com.example.usuarios.cl.usuarios.model;

import com.fasterxml.jackson.annotation.JsonProperty;

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
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 40)
    private String nombre;

    @Column(nullable = false, unique = true, length = 40)
    private String correo;
    
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(nullable = false, unique = true, length = 40)
    private String contrasenia;

    @ManyToOne
    @JoinColumn(name = "id_rol")
    private Rol rol;
}
