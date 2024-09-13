package com.proyectoIntegrador.PrjInt_GustavoContreras_BrendaCarbajal.servicio.impl;

import com.proyectoIntegrador.PrjInt_GustavoContreras_BrendaCarbajal.entity.Paciente;
import com.proyectoIntegrador.PrjInt_GustavoContreras_BrendaCarbajal.exception.ResourceNotFoundException;
import com.proyectoIntegrador.PrjInt_GustavoContreras_BrendaCarbajal.repository.IPacienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PacienteServicioImplTest {

    @Mock
    private IPacienteRepository pacienteRepository;

    @InjectMocks
    private PacienteServicioImpl pacienteServicio;

    private Paciente paciente;

    @BeforeEach
    void setUp() {
        paciente = new Paciente();
        paciente.setId(1L);
        paciente.setDni("12345678");
        paciente.setNombre("Carlos");
        paciente.setApellido("Sánchez");
    }

    @Test
    void testGuardar() {
        when(pacienteRepository.save(paciente)).thenReturn(paciente);

        Paciente result = pacienteServicio.guardar(paciente);

        assertNotNull(result);
        assertEquals(paciente.getDni(), result.getDni());
        verify(pacienteRepository, times(1)).save(paciente);
    }

    @Test
    void testBuscarPorId_ExistingId() {
        when(pacienteRepository.findById(1L)).thenReturn(Optional.of(paciente));

        Paciente result = pacienteServicio.buscarPorId(1L);

        assertNotNull(result);
        assertEquals(paciente.getDni(), result.getDni());
        verify(pacienteRepository, times(1)).findById(1L);
    }

    @Test
    void testBuscarPorId_NotFound() {
        when(pacienteRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            pacienteServicio.buscarPorId(1L);
        });

        assertEquals("Paciente no encontrado con id: 1", exception.getMessage());
        verify(pacienteRepository, times(1)).findById(1L);
    }

    @Test
    void testEliminar_ExistingId() {
        when(pacienteRepository.existsById(1L)).thenReturn(true);

        pacienteServicio.eliminar(1L);

        verify(pacienteRepository, times(1)).deleteById(1L);
    }

    @Test
    void testEliminar_NotFound() {
        when(pacienteRepository.existsById(1L)).thenReturn(false);

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            pacienteServicio.eliminar(1L);
        });

        assertEquals("Paciente no encontrado con id: 1", exception.getMessage());
        verify(pacienteRepository, times(1)).existsById(1L);
    }

    @Test
    void testEliminarPorDni_ExistingDni() {
        when(pacienteRepository.findByDni("12345678")).thenReturn(paciente);
        when(pacienteRepository.existsById(paciente.getId())).thenReturn(true);

        pacienteServicio.eliminar("12345678");

        verify(pacienteRepository, times(1)).deleteById(1L);
    }



    @Test
    void testListarTodos() {
        List<Paciente> pacientes = Arrays.asList(paciente, new Paciente());
        when(pacienteRepository.findAll()).thenReturn(pacientes);

        List<Paciente> result = pacienteServicio.listarTodos();

        assertEquals(2, result.size());
        verify(pacienteRepository, times(1)).findAll();
    }

    @Test
    void testBuscarPorDni_ExistingDni() {
        when(pacienteRepository.findByDni("12345678")).thenReturn(paciente);

        Paciente result = pacienteServicio.buscarPorDni("12345678");

        assertNotNull(result);
        assertEquals(paciente.getDni(), result.getDni());
        verify(pacienteRepository, times(1)).findByDni("12345678");
    }

    @Test
    void testBuscarPorDni_NotFound() {
        when(pacienteRepository.findByDni("12345678")).thenReturn(null);

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            pacienteServicio.buscarPorDni("12345678");
        });

        assertEquals("Paciente no encontrado con DNI: 12345678", exception.getMessage());
        verify(pacienteRepository, times(1)).findByDni("12345678");
    }

    @Test
    void testActualizar_ExistingId() {
        when(pacienteRepository.existsById(paciente.getId())).thenReturn(true);
        when(pacienteRepository.save(paciente)).thenReturn(paciente);

        pacienteServicio.actualizar(paciente);

        verify(pacienteRepository, times(1)).save(paciente);
    }

    @Test
    void testActualizar_NotFound() {
        when(pacienteRepository.existsById(paciente.getId())).thenReturn(false);

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            pacienteServicio.actualizar(paciente);
        });

        assertEquals("Paciente no encontrado con id: 1", exception.getMessage());
        verify(pacienteRepository, times(1)).existsById(paciente.getId());
    }
}
