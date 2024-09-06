package com.proyectoIntegrador.PrjInt_GustavoContreras_BrendaCarbajal.controller;

import com.proyectoIntegrador.PrjInt_GustavoContreras_BrendaCarbajal.entity.Turno;
import com.proyectoIntegrador.PrjInt_GustavoContreras_BrendaCarbajal.servicio.ITurnoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoController {

    @Autowired
    private ITurnoServicio iTurnoServicio;

    // Crear un nuevo turno (POST)
    @PostMapping
    public ResponseEntity<Turno> guardar(@RequestBody Turno turno) {
        Turno nuevoTurno = iTurnoServicio.guardar(turno);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoTurno);
    }


    // Obtener un turno por ID (GET)
    @GetMapping("/{id}")
    public ResponseEntity<Turno> buscarPorId(@PathVariable Long id) {
        Turno turno = iTurnoServicio.buscarPorId(id);
        return ResponseEntity.ok(turno);
    }

    // Listar todos los turnos (GET)
    @GetMapping
    public ResponseEntity<List<Turno>> listarTodos() {
        List<Turno> turnos = iTurnoServicio.listarTodos();
        return ResponseEntity.ok(turnos);
    }

    // Actualizar un turno existente (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<Turno> actualizar(@PathVariable Long id, @RequestBody Turno turno) {
        Turno turnoExistente = iTurnoServicio.buscarPorId(id);
        turnoExistente.setFecha(turno.getFecha());
        turnoExistente.setPaciente(turno.getPaciente());
        turnoExistente.setOdontologo(turno.getOdontologo());

        Turno turnoActualizado = iTurnoServicio.guardar(turnoExistente);
        return ResponseEntity.ok(turnoActualizado);
    }

    // Eliminar un turno por ID (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        iTurnoServicio.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
