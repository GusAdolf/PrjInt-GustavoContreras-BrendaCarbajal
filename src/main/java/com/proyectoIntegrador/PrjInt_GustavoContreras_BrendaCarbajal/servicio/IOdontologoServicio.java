package com.proyectoIntegrador.PrjInt_GustavoContreras_BrendaCarbajal.servicio;

import com.proyectoIntegrador.PrjInt_GustavoContreras_BrendaCarbajal.entity.Odontologo;

import java.util.List;
import java.util.Optional;

public interface IOdontologoServicio {
    Odontologo guardar (Odontologo odontologo);
    Odontologo buscarPorId(Long id);
    void eliminar(Long id);
    void actualizar (Odontologo odontologo);

    List<Odontologo> listarTodos();

    Odontologo buscarPorMatricula(Long matricula);
}
