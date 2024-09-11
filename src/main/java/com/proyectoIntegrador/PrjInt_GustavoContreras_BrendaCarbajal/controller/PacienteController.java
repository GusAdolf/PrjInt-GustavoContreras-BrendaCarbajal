package com.proyectoIntegrador.PrjInt_GustavoContreras_BrendaCarbajal.controller;

import com.proyectoIntegrador.PrjInt_GustavoContreras_BrendaCarbajal.entity.Odontologo;
import com.proyectoIntegrador.PrjInt_GustavoContreras_BrendaCarbajal.entity.Paciente;
import com.proyectoIntegrador.PrjInt_GustavoContreras_BrendaCarbajal.servicio.IPacienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private IPacienteServicio iPacienteServicio;

    @GetMapping(value = "/search/{id}")
    public ResponseEntity<Paciente> consultarPorId(@PathVariable Long id) {
        Paciente paciente = iPacienteServicio.buscarPorId(id);
        return ResponseEntity.ok(paciente);
    }

    @PostMapping(value = "/save")
    public ResponseEntity<Paciente> guardar(@RequestBody Paciente paciente) {
        return ResponseEntity.status(201).body(iPacienteServicio.guardar(paciente));
    }

    @GetMapping
    public ResponseEntity<List<Paciente>> listarTodos() {
        return ResponseEntity.ok(iPacienteServicio.listarTodos());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Paciente> actualizar(@PathVariable Long id, @RequestBody Paciente paciente) {
        Paciente pacienteExistente = iPacienteServicio.buscarPorId(id);
        if (pacienteExistente != null) {
            pacienteExistente.setNombre(paciente.getNombre());
            pacienteExistente.setApellido(paciente.getApellido());
            pacienteExistente.setDni(paciente.getDni());
            pacienteExistente.setFechaAlta(paciente.getFechaAlta());
            pacienteExistente.setDomicilio(paciente.getDomicilio());  // Si el domicilio también es actualizable

            return ResponseEntity.ok(iPacienteServicio.guardar(pacienteExistente));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        iPacienteServicio.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    // Obtener odontólogo por matrícula
    @GetMapping("/searchbyndi/{dni}")
    public ResponseEntity<Paciente> buscarPorDni(@PathVariable String dni) {
        Paciente paciente = iPacienteServicio.buscarPorDni(dni);
        return ResponseEntity.ok(paciente);
    }


    // Eliminar un paciente v2
    @PostMapping(value = "/delete")
    public ResponseEntity<Paciente> borrar(@RequestBody Paciente paciente) {

        iPacienteServicio.eliminar(paciente.getDni());
        return ResponseEntity.status(HttpStatus.OK).body(paciente);



    }
}
