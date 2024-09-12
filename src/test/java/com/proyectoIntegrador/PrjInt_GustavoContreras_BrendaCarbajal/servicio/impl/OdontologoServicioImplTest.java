package com.proyectoIntegrador.PrjInt_GustavoContreras_BrendaCarbajal.servicio.impl;

import com.proyectoIntegrador.PrjInt_GustavoContreras_BrendaCarbajal.entity.Odontologo;
import com.proyectoIntegrador.PrjInt_GustavoContreras_BrendaCarbajal.exception.ResourceNotFoundException;
import com.proyectoIntegrador.PrjInt_GustavoContreras_BrendaCarbajal.repository.IOdontologoRepository;
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

class OdontologoServicioImplTest {

    @Mock
    private IOdontologoRepository odontologoRepository;

    @InjectMocks
    private OdontologoServicioImpl odontologoServicio;

    private Odontologo odontologo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        odontologo = new Odontologo();
        odontologo.setMatricula(1L);  // Corregido para que sea un `Long`
        odontologo.setNombre("Juan");
        odontologo.setApellido("Perez");
    }

    @Test
    void guardar() {
        when(odontologoRepository.save(odontologo)).thenReturn(odontologo);

        Odontologo result = odontologoServicio.guardar(odontologo);

        assertNotNull(result);
        assertEquals("Juan", result.getNombre());
        verify(odontologoRepository, times(1)).save(odontologo);
    }

    @Test
    void buscarPorId() {
        when(odontologoRepository.findById(1L)).thenReturn(Optional.of(odontologo));

        Odontologo result = odontologoServicio.buscarPorId(1L);

        assertNotNull(result);
        assertEquals("Juan", result.getNombre());
        verify(odontologoRepository, times(1)).findById(1L);
    }

    @Test
    void buscarPorId_NotFound() {
        when(odontologoRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            odontologoServicio.buscarPorId(1L);
        });

        String expectedMessage = "Odontólogo no encontrado con id: 1";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void eliminar() {
        when(odontologoRepository.existsById(1L)).thenReturn(true);

        odontologoServicio.eliminar(1L);

        verify(odontologoRepository, times(1)).deleteById(1L);
    }

    @Test
    void eliminar_NotFound() {
        when(odontologoRepository.existsById(1L)).thenReturn(false);

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            odontologoServicio.eliminar(1L);
        });

        String expectedMessage = "Odontólogo no encontrado con id: 1";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void actualizar() {
        when(odontologoRepository.existsById(odontologo.getMatricula())).thenReturn(true);
        when(odontologoRepository.save(odontologo)).thenReturn(odontologo);

        odontologoServicio.actualizar(odontologo);

        verify(odontologoRepository, times(1)).save(odontologo);
    }

    @Test
    void actualizar_NotFound() {
        when(odontologoRepository.existsById(odontologo.getMatricula())).thenReturn(false);

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            odontologoServicio.actualizar(odontologo);
        });

        String expectedMessage = "Odontólogo no encontrado con id: " + odontologo.getMatricula();
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void listarTodos() {
        List<Odontologo> odontologos = Arrays.asList(odontologo, new Odontologo());
        when(odontologoRepository.findAll()).thenReturn(odontologos);

        List<Odontologo> result = odontologoServicio.listarTodos();

        assertEquals(2, result.size());
        verify(odontologoRepository, times(1)).findAll();
    }

    @Test
    void buscarPorMatricula() {
        when(odontologoRepository.findByMatricula(1L)).thenReturn(odontologo);

        Odontologo result = odontologoServicio.buscarPorMatricula(1L);

        assertNotNull(result);
        assertEquals("Juan", result.getNombre());
        verify(odontologoRepository, times(1)).findByMatricula(1L);
    }

    @Test
    void buscarPorMatricula_NotFound() {
        when(odontologoRepository.findByMatricula(1L)).thenReturn(null);

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            odontologoServicio.buscarPorMatricula(1L);
        });

        String expectedMessage = "Odontólogo no encontrado con matrícula: 1";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}
