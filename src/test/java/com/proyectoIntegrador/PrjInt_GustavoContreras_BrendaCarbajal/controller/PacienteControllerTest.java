package com.proyectoIntegrador.PrjInt_GustavoContreras_BrendaCarbajal.controller;

import com.proyectoIntegrador.PrjInt_GustavoContreras_BrendaCarbajal.entity.Paciente;
import com.proyectoIntegrador.PrjInt_GustavoContreras_BrendaCarbajal.servicio.IPacienteServicio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PacienteControllerTest {

    @Mock
    private IPacienteServicio iPacienteServicio;

    @InjectMocks
    private PacienteController pacienteController;

    private Paciente paciente1;
    private Paciente paciente2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Inicializamos algunos objetos de ejemplo para las pruebas
        paciente1 = new Paciente(1L, "Juan", "Pérez", "12345678", LocalDate.now(), "Domicilio 1");
        paciente2 = new Paciente(2L, "Ana", "López", "87654321", LocalDate.now(), "Domicilio 2");
    }

    @Test
    void testConsultarPorId() {
        // Configuramos el comportamiento del mock
        when(iPacienteServicio.buscarPorId(1L)).thenReturn(paciente1);

        // Llamamos al método del controlador
        ResponseEntity<Paciente> response = pacienteController.consultarPorId(1L);

        // Verificamos el comportamiento y las respuestas
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(paciente1, response.getBody());
        verify(iPacienteServicio, times(1)).buscarPorId(1L);
    }

    @Test
    void testGuardarPaciente() {
        when(iPacienteServicio.guardar(paciente1)).thenReturn(paciente1);

        ResponseEntity<Paciente> response = pacienteController.guardar(paciente1);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(paciente1, response.getBody());
        verify(iPacienteServicio, times(1)).guardar(paciente1);
    }

    @Test
    void testListarTodos() {
        List<Paciente> pacientes = Arrays.asList(paciente1, paciente2);
        when(iPacienteServicio.listarTodos()).thenReturn(pacientes);

        ResponseEntity<List<Paciente>> response = pacienteController.listarTodos();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(pacientes, response.getBody());
        verify(iPacienteServicio, times(1)).listarTodos();
    }

    @Test
    void testActualizarPaciente() {
        when(iPacienteServicio.buscarPorId(1L)).thenReturn(paciente1);
        when(iPacienteServicio.guardar(any(Paciente.class))).thenReturn(paciente1);

        Paciente pacienteActualizado = new Paciente(1L, "Carlos", "Rodríguez", "12345678", LocalDate.now(), "Domicilio actualizado");
        ResponseEntity<Paciente> response = pacienteController.actualizar("12345678", pacienteActualizado);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(iPacienteServicio, times(1)).buscarPorDni("12345678");
        verify(iPacienteServicio, times(1)).guardar(any(Paciente.class));
    }

    @Test
    void testEliminarPaciente() {
        doNothing().when(iPacienteServicio).eliminar(1L);

        ResponseEntity<Void> response = pacienteController.eliminar(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(iPacienteServicio, times(1)).eliminar(1L);
    }
}
