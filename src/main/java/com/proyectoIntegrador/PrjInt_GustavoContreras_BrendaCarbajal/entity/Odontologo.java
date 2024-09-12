package com.proyectoIntegrador.PrjInt_GustavoContreras_BrendaCarbajal.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "odontologo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Odontologo {


    @Id
    @Column(name = "matricula", nullable = false, unique = true)
    private Long matricula;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "apellido", nullable = false)
    private String apellido;
}
