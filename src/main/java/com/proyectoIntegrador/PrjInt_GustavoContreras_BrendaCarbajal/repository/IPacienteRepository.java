package com.proyectoIntegrador.PrjInt_GustavoContreras_BrendaCarbajal.repository;


import com.proyectoIntegrador.PrjInt_GustavoContreras_BrendaCarbajal.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IPacienteRepository  extends JpaRepository<Paciente, Long> {
}
