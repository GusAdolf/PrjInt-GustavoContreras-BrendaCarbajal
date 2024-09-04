package com.proyectoIntegrador.PrjInt_GustavoContreras_BrendaCarbajal.repository;


import com.proyectoIntegrador.PrjInt_GustavoContreras_BrendaCarbajal.entity.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface IOdontologoRepository extends JpaRepository<Odontologo, Long> {

    //    @Query("SELECT o FROM Odontologo o WHERE o.matricula = ?1")
    Odontologo findByMatricula (String matricula);

}
