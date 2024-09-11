package com.proyectoIntegrador.PrjInt_GustavoContreras_BrendaCarbajal.servicio.impl;

import com.proyectoIntegrador.PrjInt_GustavoContreras_BrendaCarbajal.entity.Odontologo;
import com.proyectoIntegrador.PrjInt_GustavoContreras_BrendaCarbajal.exception.ResourceNotFoundException;
import com.proyectoIntegrador.PrjInt_GustavoContreras_BrendaCarbajal.repository.IOdontologoRepository;
import com.proyectoIntegrador.PrjInt_GustavoContreras_BrendaCarbajal.servicio.IOdontologoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoServicioImpl implements IOdontologoServicio {
    @Autowired
    private IOdontologoRepository iOdontologoRepository;

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        return iOdontologoRepository.save(odontologo);
    }

    @Override
    public Odontologo buscarPorId(Long id) {
        return iOdontologoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Odontólogo no encontrado con id: " + id));
    }

    @Override
    public void eliminar(Long id) {
        if (!iOdontologoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Odontólogo no encontrado con id: " + id);
        }
        iOdontologoRepository.deleteById(id);
    }

    @Override
    public void actualizar(Odontologo odontologo) {
        if (!iOdontologoRepository.existsById(odontologo.getMatricula())) {
            throw new ResourceNotFoundException("Odontólogo no encontrado con id: " + odontologo.getMatricula());
        }
        iOdontologoRepository.save(odontologo);
    }

    @Override
    public List<Odontologo> listarTodos() {
        return iOdontologoRepository.findAll();
    }

    @Override
    public Odontologo buscarPorMatricula(Long matricula) {
        Odontologo odontologo = iOdontologoRepository.findByMatricula(matricula);
        if (odontologo == null) {
            throw new ResourceNotFoundException("Odontólogo no encontrado con matrícula: " + matricula);
        }
        return odontologo;
    }
}
