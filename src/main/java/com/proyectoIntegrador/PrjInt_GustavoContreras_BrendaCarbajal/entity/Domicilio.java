package com.proyectoIntegrador.PrjInt_GustavoContreras_BrendaCarbajal.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "domicilios")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Domicilio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String calle;
    private String numero;
    private String ciudad;
    private String provincia;

    // Relación inversa con Paciente (opcional para evitar recursividad en JSON)
    @OneToOne(mappedBy = "domicilio")
    @JsonIgnore
    private Paciente paciente;
}
