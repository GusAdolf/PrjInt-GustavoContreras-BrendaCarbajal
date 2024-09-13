package com.proyectoIntegrador.PrjInt_GustavoContreras_BrendaCarbajal.servicio.impl;

import com.proyectoIntegrador.PrjInt_GustavoContreras_BrendaCarbajal.entity.Odontologo;
import com.proyectoIntegrador.PrjInt_GustavoContreras_BrendaCarbajal.entity.Paciente;
import com.proyectoIntegrador.PrjInt_GustavoContreras_BrendaCarbajal.entity.Turno;
import com.proyectoIntegrador.PrjInt_GustavoContreras_BrendaCarbajal.exception.ResourceNotFoundException;
import com.proyectoIntegrador.PrjInt_GustavoContreras_BrendaCarbajal.repository.ITurnoRepository;
import com.proyectoIntegrador.PrjInt_GustavoContreras_BrendaCarbajal.servicio.IOdontologoServicio;
import com.proyectoIntegrador.PrjInt_GustavoContreras_BrendaCarbajal.servicio.IPacienteServicio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TurnoServicioImplTest {

    @Mock
    private ITurnoRepository turnoRepository;

    @Mock
    private IOdontologoServicio odontologoServicio;

    @Mock
    private IPacienteServicio pacienteServicio;

    @InjectMocks
    private TurnoServicioImpl turnoServicio;

    private Turno turno;
    private Paciente paciente;
    private Odontologo odontologo;

    @BeforeEach
    void setUp() {
        paciente = new Paciente();
        paciente.setId(1L);
        paciente.setNombre("Carlos");

        odontologo = new Odontologo();
        odontologo.setMatricula(101L);
        odontologo.setNombre("Dr. Juan");

        turno = new Turno();
        turno.setId(1L);
        turno.setPaciente(paciente);
        turno.setOdontologo(odontologo);
        turno.setFecha(LocalDate.parse("2024-09-15"));
    }

    @Test
    void testGuardar() {
        when(pacienteServicio.buscarPorId(1L)).thenReturn(paciente);
        when(odontologoServicio.buscarPorId(101L)).thenReturn(odontologo);
        when(turnoRepository.save(turno)).thenReturn(turno);

        Turno result = turnoServicio.guardar(turno);

        assertNotNull(result);
        assertEquals(turno.getFecha(), result.getFecha());
        assertEquals(turno.getPaciente().getId(), result.getPaciente().getId());
        assertEquals(turno.getOdontologo().getMatricula(), result.getOdontologo().getMatricula());
        verify(turnoRepository, times(1)).save(turno);
    }

    @Test
    void testGuardar_PacienteNoEncontrado() {
        when(pacienteServicio.buscarPorId(1L)).thenThrow(new ResourceNotFoundException("Paciente no encontrado con id: 1"));

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            turnoServicio.guardar(turno);
        });

        assertEquals("Paciente no encontrado con id: 1", exception.getMessage());
        verify(turnoRepository, never()).save(turno);
    }

    @Test
    void testGuardar_OdontologoNoEncontrado() {
        when(pacienteServicio.buscarPorId(1L)).thenReturn(paciente);
        when(odontologoServicio.buscarPorId(101L)).thenThrow(new ResourceNotFoundException("Odontólogo no encontrado con id: 101"));

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            turnoServicio.guardar(turno);
        });

        assertEquals("Odontólogo no encontrado con id: 101", exception.getMessage());
        verify(turnoRepository, never()).save(turno);
    }

    @Test
    void testBuscarPorId_ExistingId() {
        when(turnoRepository.findById(1L)).thenReturn(Optional.of(turno));

        Turno result = turnoServicio.buscarPorId(1L);

        assertNotNull(result);
        assertEquals(turno.getFecha(), result.getFecha());
        verify(turnoRepository, times(1)).findById(1L);
    }

    @Test
    void testBuscarPorId_NotFound() {
        when(turnoRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            turnoServicio.buscarPorId(1L);
        });

        assertEquals("Turno no encontrado con id: 1", exception.getMessage());
        verify(turnoRepository, times(1)).findById(1L);
    }

    @Test
    void testListarTodos() {
        List<Turno> turnos = Arrays.asList(turno, new Turno());
        when(turnoRepository.findAll()).thenReturn(turnos);

        List<Turno> result = turnoServicio.listarTodos();

        assertEquals(2, result.size());
        verify(turnoRepository, times(1)).findAll();
    }

    @Test
    void testEliminar_ExistingId() {
        when(turnoRepository.existsById(1L)).thenReturn(true);

        turnoServicio.eliminar(1L);

        verify(turnoRepository, times(1)).deleteById(1L);
    }

    @Test
    void testEliminar_NotFound() {
        when(turnoRepository.existsById(1L)).thenReturn(false);

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            turnoServicio.eliminar(1L);
        });

        assertEquals("Turno no encontrado con id: 1", exception.getMessage());
        verify(turnoRepository, never()).deleteById(1L);
    }
}
