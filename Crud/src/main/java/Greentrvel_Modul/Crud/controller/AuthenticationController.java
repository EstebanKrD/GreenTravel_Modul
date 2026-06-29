package Greentrvel_Modul.Crud.controller;

import Greentrvel_Modul.Crud.dto.LoginRequest;
import Greentrvel_Modul.Crud.dto.RegistroRequest;
import Greentrvel_Modul.Crud.dto.AuthResponse;
import Greentrvel_Modul.Crud.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador encargado de gestionar los procesos de autenticación.
 * Permite registrar nuevos usuarios e iniciar sesión mediante JWT.
 */
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    /**
     * Servicio encargado de la lógica de autenticación y registro.
     */
    private final AuthenticationService authenticationService;

    /**
     * Constructor encargado de inyectar el servicio de autenticación.
     */
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    /**
     * Endpoint encargado de registrar un nuevo usuario.
     * Recibe los datos de registro, los valida y delega el proceso al servicio de autenticación.
     */
    @PostMapping("/register")
    public AuthResponse register(@Valid @RequestBody RegistroRequest request) {

        return authenticationService.register(request);

    }

    /**
     * Endpoint encargado de autenticar un usuario existente.
     * Recibe las credenciales de acceso, las valida y genera un token JWT para el usuario autenticado.
     */
    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody LoginRequest request) {

        return authenticationService.login(request);

    }

}