package com.proyectoIntegrador.PrjInt_GustavoContreras_BrendaCarbajal.controller;

import com.proyectoIntegrador.PrjInt_GustavoContreras_BrendaCarbajal.entity.Paciente;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String redirectToLogin() {
        return "login/login";
    }

    @GetMapping("/menu")
    public String redirectToMenu() {
        return "menu/menu.html";
    }

    @GetMapping("/registro")
    public String redirectToRegistro() {
        return "admin/registro.html";
    }

    @GetMapping("/odontologos-listar")
    public String redirectToListarOdontologos() {
        return "odontologos/listarOdontologos.html"; // Redirige a la lista de odontólogos
    }

    @GetMapping("/odontologos-agregar")
    public String redirectToAgregarOdontologos() {
        return "odontologos/agregarOdontologos.html"; // Redirige a la página de agregar los odontólogos
    }

    @GetMapping("/pacientes-agregar")
    public String redirectToAgregarPacientes() {
        return "pacientes/agregarPacientes.html"; // Redirige a la página de agregar los odontólogos
    }


    @GetMapping("/pacientes-listar")
    public String redirectToListarPacientes() {
        return "pacientes/listarPacientes.html"; // Redirige a la lista de pacientes
    }


    @GetMapping("/odontologos-modificar")
    public String redirectToModificarOdontologos() {
        return "odontologos/modificarOdontologos.html"; // Redirige a la modificación de odontólogos
    }


    @GetMapping("/pacientes-modificar")
    public String redirectToModificarPacientes() {
        return "pacientes/modificarPacientes.html"; // Redirige a la página de modificar pacientes
    }

    @GetMapping("/agendar-turnos")
    public String redirectToTurnos() {
        return "turnos/turnos.html"; // Redirige a la página del turnos
    }

    @GetMapping("/listar-turnos")
    public String redirectToListTurnos() {
        return "turnos/listarTurnos.html"; // Redirige a la página del turnos
    }

    @GetMapping("/odontologos-eliminar")
    public String redirectToEliminarOdontologos() {
        return "odontologos/eliminarOdontologos.html"; // Redirige a la eliminación de odontólogos
    }

    @GetMapping("/pacientes-eliminar")
    public String redirectToEliminarPacientes() {
        return "pacientes/eliminarPacientes.html"; // Redirige a la página de eliminar pacientes
    }


}
