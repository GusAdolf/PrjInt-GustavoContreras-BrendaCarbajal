package com.proyectoIntegrador.PrjInt_GustavoContreras_BrendaCarbajal.controller;

import com.proyectoIntegrador.PrjInt_GustavoContreras_BrendaCarbajal.entity.Odontologo;
import com.proyectoIntegrador.PrjInt_GustavoContreras_BrendaCarbajal.servicio.IOdontologoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {

    @Autowired
    private IOdontologoServicio odontologoServicio;

    // Obtener odontólogo por ID
    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> buscarPorId(@PathVariable Long id) {
        Odontologo odontologo = odontologoServicio.buscarPorId(id);
        return ResponseEntity.ok(odontologo);
    }

    // Crear un nuevo odontólogo
    @PostMapping(value = "/save")
    public ResponseEntity<Odontologo> guardar(@RequestBody Odontologo odontologo) {
        return ResponseEntity.status(HttpStatus.CREATED).body(odontologoServicio.guardar(odontologo));
    }

    // Obtener todos los odontólogos
    @GetMapping(value = "/findAll")
    public ResponseEntity<List<Odontologo>> listarTodos() {
        return ResponseEntity.ok(odontologoServicio.listarTodos());
    }

    // Obtener odontólogo por matrícula
    @GetMapping("/search/{matricula}")
    public ResponseEntity<Odontologo> buscarPorMatricula(@PathVariable Long matricula) {
        Odontologo odontologo = odontologoServicio.buscarPorMatricula(matricula);
        return ResponseEntity.ok(odontologo);
    }

    // Actualizar odontólogo existente
    @PutMapping("/update/{matricula}")
    public ResponseEntity<Odontologo> actualizar(@PathVariable Long id, @RequestBody Odontologo odontologo) {
        Odontologo odontologoExistente = odontologoServicio.buscarPorId(id);

        if (odontologoExistente != null) {
            odontologoExistente.setNombre(odontologo.getNombre());
            odontologoExistente.setApellido(odontologo.getApellido());
            odontologoExistente.setMatricula(odontologo.getMatricula());

            Odontologo odontologoActualizado = odontologoServicio.guardar(odontologoExistente);
            return ResponseEntity.ok(odontologoActualizado);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Eliminar odontólogo por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        odontologoServicio.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    // Eliminar un odontólogo v2
    @PostMapping(value = "/delete")
    public ResponseEntity<Odontologo>  borrar(@RequestBody Odontologo odontologo) {

        odontologoServicio.eliminar(odontologo.getMatricula());
        return ResponseEntity.status(HttpStatus.OK).body(odontologo);



    }
}
