package com.proyectoIntegrador.PrjInt_GustavoContreras_BrendaCarbajal.servicio;

import com.proyectoIntegrador.PrjInt_GustavoContreras_BrendaCarbajal.entity.Turno;

import java.util.List;

public interface ITurnoServicio {

    Turno guardar(Turno turno);
    Turno buscarPorId(Long id);
    List<Turno> listarTodos();
}
