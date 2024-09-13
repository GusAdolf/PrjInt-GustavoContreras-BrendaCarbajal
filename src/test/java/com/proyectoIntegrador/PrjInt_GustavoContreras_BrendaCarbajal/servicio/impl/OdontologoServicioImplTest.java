package com.proyectoIntegrador.PrjInt_GustavoContreras_BrendaCarbajal.servicio.impl;

import com.proyectoIntegrador.PrjInt_GustavoContreras_BrendaCarbajal.entity.Odontologo;
import com.proyectoIntegrador.PrjInt_GustavoContreras_BrendaCarbajal.exception.ResourceNotFoundException;
import com.proyectoIntegrador.PrjInt_GustavoContreras_BrendaCarbajal.repository.IOdontologoRepository;
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
class OdontologoServicioImplTest {

    @Mock
    private IOdontologoRepository odontologoRepository;

    @InjectMocks
    private OdontologoServicioImpl odontologoServicio;

    private Odontologo odontologo;

    @BeforeEach
    void setUp() {
        odontologo = new Odontologo();
        odontologo.setMatricula(123L);
        odontologo.setNombre("Juan");
        odontologo.setApellido("Perez");
    }

    @Test
    void testGuardar() {
        when(odontologoRepository.save(odontologo)).thenReturn(odontologo);

        Odontologo result = odontologoServicio.guardar(odontologo);

        assertNotNull(result);
        assertEquals(odontologo.getMatricula(), result.getMatricula());
        verify(odontologoRepository, times(1)).save(odontologo);
    }

    @Test
    void testBuscarPorId_ExistingId() {
        when(odontologoRepository.findById(123L)).thenReturn(Optional.of(odontologo));

        Odontologo result = odontologoServicio.buscarPorId(123L);

        assertNotNull(result);
        assertEquals(odontologo.getMatricula(), result.getMatricula());
        verify(odontologoRepository, times(1)).findById(123L);
    }

    @Test
    void testBuscarPorId_NotFound() {
        when(odontologoRepository.findById(123L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            odontologoServicio.buscarPorId(123L);
        });

        assertEquals("Odontólogo no encontrado con id: 123", exception.getMessage());
        verify(odontologoRepository, times(1)).findById(123L);
    }

    @Test
    void testEliminar_ExistingId() {
        when(odontologoRepository.existsById(123L)).thenReturn(true);

        odontologoServicio.eliminar(123L);

        verify(odontologoRepository, times(1)).deleteById(123L);
    }

    @Test
    void testEliminar_NotFound() {
        when(odontologoRepository.existsById(123L)).thenReturn(false);

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            odontologoServicio.eliminar(123L);
        });

        assertEquals("Odontólogo no encontrado con id: 123", exception.getMessage());
        verify(odontologoRepository, times(1)).existsById(123L);
    }

    @Test
    void testListarTodos() {
        List<Odontologo> odontologos = Arrays.asList(odontologo, new Odontologo());
        when(odontologoRepository.findAll()).thenReturn(odontologos);

        List<Odontologo> result = odontologoServicio.listarTodos();

        assertEquals(2, result.size());
        verify(odontologoRepository, times(1)).findAll();
    }

    @Test
    void testBuscarPorMatricula_ExistingMatricula() {
        when(odontologoRepository.findByMatricula(123L)).thenReturn(odontologo);

        Odontologo result = odontologoServicio.buscarPorMatricula(123L);

        assertNotNull(result);
        assertEquals(odontologo.getMatricula(), result.getMatricula());
        verify(odontologoRepository, times(1)).findByMatricula(123L);
    }

    @Test
    void testBuscarPorMatricula_NotFound() {
        when(odontologoRepository.findByMatricula(123L)).thenReturn(null);

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            odontologoServicio.buscarPorMatricula(123L);
        });

        assertEquals("Odontólogo no encontrado con matrícula: 123", exception.getMessage());
        verify(odontologoRepository, times(1)).findByMatricula(123L);
    }
}
