package com.proyectoIntegrador.PrjInt_GustavoContreras_BrendaCarbajal.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Turno {

    @Id
    private Long id;
    //    private Odontologo odontologo;
    // TODO POR REALIZAR LA VINCULACION CON ODONTOLOGO

    @ManyToOne
    private Paciente paciente;

    private LocalDate fecha;



}
