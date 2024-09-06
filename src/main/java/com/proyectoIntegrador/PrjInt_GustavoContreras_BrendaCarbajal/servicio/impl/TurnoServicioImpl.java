package com.proyectoIntegrador.PrjInt_GustavoContreras_BrendaCarbajal.servicio.impl;

import com.proyectoIntegrador.PrjInt_GustavoContreras_BrendaCarbajal.entity.Odontologo;
import com.proyectoIntegrador.PrjInt_GustavoContreras_BrendaCarbajal.entity.Paciente;
import com.proyectoIntegrador.PrjInt_GustavoContreras_BrendaCarbajal.entity.Turno;
import com.proyectoIntegrador.PrjInt_GustavoContreras_BrendaCarbajal.exception.ResourceNotFoundException;
import com.proyectoIntegrador.PrjInt_GustavoContreras_BrendaCarbajal.repository.ITurnoRepository;
import com.proyectoIntegrador.PrjInt_GustavoContreras_BrendaCarbajal.servicio.IOdontologoServicio;
import com.proyectoIntegrador.PrjInt_GustavoContreras_BrendaCarbajal.servicio.IPacienteServicio;
import com.proyectoIntegrador.PrjInt_GustavoContreras_BrendaCarbajal.servicio.ITurnoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurnoServicioImpl implements ITurnoServicio {

    @Autowired
    private ITurnoRepository iTurnoRepository;

    @Autowired
    private IOdontologoServicio odontologoServicio;

    @Autowired
    private IPacienteServicio pacienteServicio;

    @Override
    public Turno guardar(Turno turno) {
        // Validar que el paciente existe
        Paciente paciente = pacienteServicio.buscarPorId(turno.getPaciente().getId());
        if (paciente == null) {
            throw new ResourceNotFoundException("Paciente no encontrado con id: " + turno.getPaciente().getId());
        }

        // Validar que el odontólogo existe
        Odontologo odontologo = odontologoServicio.buscarPorId(turno.getOdontologo().getId());
        if (odontologo == null) {
            throw new ResourceNotFoundException("Odontólogo no encontrado con id: " + turno.getOdontologo().getId());
        }

        // Asignar las entidades completas al turno
        turno.setPaciente(paciente);
        turno.setOdontologo(odontologo);

        // Guardar el turno con las entidades completas
        return iTurnoRepository.save(turno);
    }

    @Override
    public Turno buscarPorId(Long id) {
        return iTurnoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Turno no encontrado con id: " + id));
    }

    @Override
    public List<Turno> listarTodos() {
        return iTurnoRepository.findAll();
    }

    @Override
    public void eliminar(Long id) {
        if (!iTurnoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Turno no encontrado con id: " + id);
        }
        iTurnoRepository.deleteById(id);
    }
}
