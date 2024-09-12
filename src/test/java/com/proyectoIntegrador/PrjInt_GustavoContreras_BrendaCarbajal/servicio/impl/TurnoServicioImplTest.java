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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TurnoServicioImplTest {

    @InjectMocks
    private TurnoServicioImpl turnoServicio;

    @Mock
    private ITurnoRepository turnoRepository;

    @Mock
    private IOdontologoServicio odontologoServicio;

    @Mock
    private IPacienteServicio pacienteServicio;

    private Turno turno;
    private Paciente paciente;
    private Odontologo odontologo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Crear un paciente de prueba
        paciente = new Paciente();
        paciente.setId(1L);
        paciente.setNombre("PacienteTest");

        // Crear un odontólogo de prueba
        odontologo = new Odontologo();
        odontologo.setMatricula(1L);  // Cambiado a Long, no String
        odontologo.setNombre("OdontologoTest");

        // Crear un turno de prueba
        turno = new Turno();
        turno.setId(1L);
        turno.setPaciente(paciente);
        turno.setOdontologo(odontologo);
    }

    @Test
    void testGuardar_TurnoExitoso() {
        // Configurar mocks
        when(pacienteServicio.buscarPorId(1L)).thenReturn(paciente);
        when(odontologoServicio.buscarPorId(1L)).thenReturn(odontologo);
        when(turnoRepository.save(turno)).thenReturn(turno);

        // Llamar al método
        Turno turnoGuardado = turnoServicio.guardar(turno);

        // Verificar resultados
        assertNotNull(turnoGuardado);
        assertEquals("PacienteTest", turnoGuardado.getPaciente().getNombre());
        assertEquals("OdontologoTest", turnoGuardado.getOdontologo().getNombre());

        // Verificar que los métodos de los servicios fueron llamados
        verify(pacienteServicio, times(1)).buscarPorId(1L);
        verify(odontologoServicio, times(1)).buscarPorId(1L);
        verify(turnoRepository, times(1)).save(turno);
    }

    @Test
    void testGuardar_PacienteNoExiste() {
        // Configurar mocks para que el paciente no exista
        when(pacienteServicio.buscarPorId(1L)).thenThrow(new ResourceNotFoundException("Paciente no encontrado con id: 1"));

        // Verificar excepción
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            turnoServicio.guardar(turno);
        });

        assertEquals("Paciente no encontrado con id: 1", exception.getMessage());

        // Verificar que el método de guardar no se llamó
        verify(turnoRepository, times(0)).save(turno);
    }

    @Test
    void testGuardar_OdontologoNoExiste() {
        // Configurar mocks para que el paciente exista pero el odontólogo no
        when(pacienteServicio.buscarPorId(1L)).thenReturn(paciente);
        when(odontologoServicio.buscarPorId(1L)).thenThrow(new ResourceNotFoundException("Odontólogo no encontrado con id: 1"));

        // Verificar excepción
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            turnoServicio.guardar(turno);
        });

        assertEquals("Odontólogo no encontrado con id: 1", exception.getMessage());

        // Verificar que el método de guardar no se llamó
        verify(turnoRepository, times(0)).save(turno);
    }

    @Test
    void testBuscarPorId_Encontrado() {
        // Configurar mocks para que el turno exista
        when(turnoRepository.findById(1L)).thenReturn(Optional.of(turno));

        // Llamar al método
        Turno turnoEncontrado = turnoServicio.buscarPorId(1L);

        // Verificar resultados
        assertNotNull(turnoEncontrado);
        assertEquals(turno.getId(), turnoEncontrado.getId());
        verify(turnoRepository, times(1)).findById(1L);
    }

    @Test
    void testBuscarPorId_NoEncontrado() {
        // Configurar mocks para que el turno no exista
        when(turnoRepository.findById(1L)).thenReturn(Optional.empty());

        // Verificar excepción
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            turnoServicio.buscarPorId(1L);
        });

        assertEquals("Turno no encontrado con id: 1", exception.getMessage());
        verify(turnoRepository, times(1)).findById(1L);
    }

    @Test
    void testListarTodos() {
        // Configurar mocks para devolver una lista de turnos
        List<Turno> turnos = Arrays.asList(turno, new Turno());
        when(turnoRepository.findAll()).thenReturn(turnos);

        // Llamar al método
        List<Turno> result = turnoServicio.listarTodos();

        // Verificar resultados
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(turnoRepository, times(1)).findAll();
    }

    @Test
    void testEliminar_Existe() {
        // Configurar mocks para que el turno exista
        when(turnoRepository.existsById(1L)).thenReturn(true);
        doNothing().when(turnoRepository).deleteById(1L);

        // Llamar al método
        turnoServicio.eliminar(1L);

        // Verificar que se llamó correctamente el método de eliminación
        verify(turnoRepository, times(1)).existsById(1L);
        verify(turnoRepository, times(1)).deleteById(1L);
    }

    @Test
    void testEliminar_NoExiste() {
        // Configurar mocks para que el turno no exista
        when(turnoRepository.existsById(1L)).thenReturn(false);

        // Verificar excepción
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            turnoServicio.eliminar(1L);
        });

        assertEquals("Turno no encontrado con id: 1", exception.getMessage());
        verify(turnoRepository, times(1)).existsById(1L);
        verify(turnoRepository, times(0)).deleteById(1L);
    }
}
