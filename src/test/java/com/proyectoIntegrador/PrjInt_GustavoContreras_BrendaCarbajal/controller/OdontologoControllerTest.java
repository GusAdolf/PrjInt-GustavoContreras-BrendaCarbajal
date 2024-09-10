package com.proyectoIntegrador.PrjInt_GustavoContreras_BrendaCarbajal.controller;

import com.proyectoIntegrador.PrjInt_GustavoContreras_BrendaCarbajal.entity.Odontologo;
import com.proyectoIntegrador.PrjInt_GustavoContreras_BrendaCarbajal.servicio.IOdontologoServicio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OdontologoControllerTest {

    @Mock
    private IOdontologoServicio odontologoServicio;

    @InjectMocks
    private OdontologoController odontologoController;

    private Odontologo odontologo1;
    private Odontologo odontologo2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Inicializamos algunos objetos de ejemplo para las pruebas
        odontologo1 = new Odontologo(1L, "Juan", "Pérez", "MAT123");
        odontologo2 = new Odontologo(2L, "Ana", "López", "MAT456");
    }

    @Test
    void testBuscarPorId() {
        // Configuramos el comportamiento del mock
        when(odontologoServicio.buscarPorId(1L)).thenReturn(odontologo1);

        // Llamamos al método del controlador
        ResponseEntity<Odontologo> response = odontologoController.buscarPorId(1L);

        // Verificamos el comportamiento y las respuestas
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(odontologo1, response.getBody());
        verify(odontologoServicio, times(1)).buscarPorId(1L);
    }

    @Test
    void testGuardarOdontologo() {
        when(odontologoServicio.guardar(odontologo1)).thenReturn(odontologo1);

        ResponseEntity<Odontologo> response = odontologoController.guardar(odontologo1);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(odontologo1, response.getBody());
        verify(odontologoServicio, times(1)).guardar(odontologo1);
    }

    @Test
    void testListarTodos() {
        List<Odontologo> odontologos = Arrays.asList(odontologo1, odontologo2);
        when(odontologoServicio.listarTodos()).thenReturn(odontologos);

        ResponseEntity<List<Odontologo>> response = odontologoController.listarTodos();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(odontologos, response.getBody());
        verify(odontologoServicio, times(1)).listarTodos();
    }

    @Test
    void testBuscarPorMatricula() {
        when(odontologoServicio.buscarPorMatricula("MAT123")).thenReturn(odontologo1);

        ResponseEntity<Odontologo> response = odontologoController.buscarPorMatricula("MAT123");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(odontologo1, response.getBody());
        verify(odontologoServicio, times(1)).buscarPorMatricula("MAT123");
    }

    @Test
    void testActualizarOdontologo() {
        when(odontologoServicio.buscarPorId(1L)).thenReturn(odontologo1);
        when(odontologoServicio.guardar(odontologo1)).thenReturn(odontologo1);

        odontologo1.setNombre("Carlos");
        ResponseEntity<Odontologo> response = odontologoController.actualizar(1L, odontologo1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(odontologo1, response.getBody());
        verify(odontologoServicio, times(1)).buscarPorId(1L);
        verify(odontologoServicio, times(1)).guardar(odontologo1);
    }

    @Test
    void testEliminarOdontologo() {
        doNothing().when(odontologoServicio).eliminar(1L);

        ResponseEntity<Void> response = odontologoController.eliminar(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(odontologoServicio, times(1)).eliminar(1L);
    }
}
