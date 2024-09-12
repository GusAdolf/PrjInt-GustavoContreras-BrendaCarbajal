package com.proyectoIntegrador.PrjInt_GustavoContreras_BrendaCarbajal.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "turno")
public class Turno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "odontologo_id", referencedColumnName = "matricula", nullable = false)
    private Odontologo odontologo;

    @ManyToOne
    @JoinColumn(name = "paciente_id", referencedColumnName = "id", nullable = false)
    private Paciente paciente;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "hora", nullable = false)
    private LocalTime hora;

}
