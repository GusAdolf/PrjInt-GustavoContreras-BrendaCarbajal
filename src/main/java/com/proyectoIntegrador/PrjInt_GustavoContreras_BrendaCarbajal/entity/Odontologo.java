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

    private String nombre;
    private String apellido;

    @Id
    private Long  matricula;
}
