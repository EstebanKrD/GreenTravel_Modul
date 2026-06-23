package Greentrvel_Modul.Crud.controller;

import Greentrvel_Modul.Crud.dto.UsuarioRegistroDTO;
import Greentrvel_Modul.Crud.entity.Usuario;
import Greentrvel_Modul.Crud.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    // Conectamos el servicio de forma tradicional mediante el constructor
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // Endpoint para registrar un usuario (POST http://localhost:8080/api/usuarios)
    @PostMapping
    public ResponseEntity<Usuario> registrar(@RequestBody UsuarioRegistroDTO dto) {
        Usuario usuarioCreado = usuarioService.registrarUsuario(dto);
        return ResponseEntity.ok(usuarioCreado);
    }

    // Endpoint para listar todos los usuarios (GET http://localhost:8080/api/usuarios)
    @GetMapping
    public ResponseEntity<List<Usuario>> listar() {
        List<Usuario> usuarios = usuarioService.listarTodos();
        return ResponseEntity.ok(usuarios);
    }
}