package com.proyectoIntegrador.PrjInt_GustavoContreras_BrendaCarbajal.servicio;

import com.proyectoIntegrador.PrjInt_GustavoContreras_BrendaCarbajal.entity.Paciente;

import java.util.List;

public interface IPacienteServicio {


    Paciente guardar (Paciente paciente);
    Paciente buscarPorId(Long id);
    List<Paciente> listarTodos();
    void actualizar(Paciente paciente);
    void eliminar(Long id);

    Paciente buscarPorDni(String dni);
}
