package com.proyectoIntegrador.PrjInt_GustavoContreras_BrendaCarbajal.repository;


import com.proyectoIntegrador.PrjInt_GustavoContreras_BrendaCarbajal.entity.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITurnoRepository  extends JpaRepository<Turno, Long> {
}
