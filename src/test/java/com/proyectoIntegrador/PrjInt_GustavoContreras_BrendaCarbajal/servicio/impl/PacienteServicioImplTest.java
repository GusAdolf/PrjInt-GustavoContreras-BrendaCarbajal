package com.proyectoIntegrador.PrjInt_GustavoContreras_BrendaCarbajal.servicio.impl;

import com.proyectoIntegrador.PrjInt_GustavoContreras_BrendaCarbajal.entity.Paciente;
import com.proyectoIntegrador.PrjInt_GustavoContreras_BrendaCarbajal.exception.ResourceNotFoundException;
import com.proyectoIntegrador.PrjInt_GustavoContreras_BrendaCarbajal.repository.IPacienteRepository;
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

class PacienteServicioImplTest {

    @InjectMocks
    private PacienteServicioImpl pacienteServicio;

    @Mock
    private IPacienteRepository pacienteRepository;

    private Paciente paciente;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        paciente = new Paciente();
        paciente.setId(1L);
        paciente.setNombre("Gustavo");
        paciente.setApellido("Contreras");
        paciente.setDni("1234567890");
    }

    @Test
    void testGuardar() {
        when(pacienteRepository.save(paciente)).thenReturn(paciente);

        Paciente result = pacienteServicio.guardar(paciente);

        assertNotNull(result);
        assertEquals(paciente.getNombre(), result.getNombre());
        verify(pacienteRepository, times(1)).save(paciente);
    }

    @Test
    void testBuscarPorId_Encontrado() {
        when(pacienteRepository.findById(1L)).thenReturn(Optional.of(paciente));

        Paciente result = pacienteServicio.buscarPorId(1L);

        assertNotNull(result);
        assertEquals(paciente.getNombre(), result.getNombre());
        verify(pacienteRepository, times(1)).findById(1L);
    }

    @Test
    void testBuscarPorId_NoEncontrado() {
        when(pacienteRepository.findById(1L)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            pacienteServicio.buscarPorId(1L);
        });

        assertEquals("Paciente no encontrado con id: 1", exception.getMessage());
        verify(pacienteRepository, times(1)).findById(1L);
    }

    @Test
    void testListarTodos() {
        List<Paciente> pacientes = Arrays.asList(paciente, new Paciente());
        when(pacienteRepository.findAll()).thenReturn(pacientes);

        List<Paciente> result = pacienteServicio.listarTodos();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(pacienteRepository, times(1)).findAll();
    }

    @Test
    void testActualizar_Existe() {
        when(pacienteRepository.existsById(1L)).thenReturn(true);
        when(pacienteRepository.save(paciente)).thenReturn(paciente);

        pacienteServicio.actualizar(paciente);

        verify(pacienteRepository, times(1)).existsById(1L);
        verify(pacienteRepository, times(1)).save(paciente);
    }

    @Test
    void testActualizar_NoExiste() {
        when(pacienteRepository.existsById(1L)).thenReturn(false);

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            pacienteServicio.actualizar(paciente);
        });

        assertEquals("Paciente no encontrado con id: 1", exception.getMessage());
        verify(pacienteRepository, times(1)).existsById(1L);
        verify(pacienteRepository, times(0)).save(paciente);
    }

    @Test
    void testEliminar_Existe() {
        when(pacienteRepository.existsById(1L)).thenReturn(true);
        doNothing().when(pacienteRepository).deleteById(1L);

        pacienteServicio.eliminar(1L);

        verify(pacienteRepository, times(1)).existsById(1L);
        verify(pacienteRepository, times(1)).deleteById(1L);
    }

    @Test
    void testEliminar_NoExiste() {
        when(pacienteRepository.existsById(1L)).thenReturn(false);

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            pacienteServicio.eliminar(1L);
        });

        assertEquals("Paciente no encontrado con id: 1", exception.getMessage());
        verify(pacienteRepository, times(1)).existsById(1L);
        verify(pacienteRepository, times(0)).deleteById(1L);
    }
}
