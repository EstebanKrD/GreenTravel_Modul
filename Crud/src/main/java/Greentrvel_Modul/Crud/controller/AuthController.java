package Greentrvel_Modul.Crud.controller;

import Greentrvel_Modul.Crud.dto.LoginRequestDTO;
import Greentrvel_Modul.Crud.dto.RegisterRequestDTO;
import Greentrvel_Modul.Crud.dto.AuthResponseDTO;
import Greentrvel_Modul.Crud.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Autenticación", description = "Registro e inicio de sesión de usuarios mediante JWT")
public class AuthController {

    /**
     * Servicio encargado de la lógica de autenticación y registro.
     */
    private final AuthenticationService authenticationService;

    /**
     * Constructor encargado de inyectar el servicio de autenticación.
     */
    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    /**
     * Endpoint encargado de registrar un nuevo usuario.
     * Recibe los datos de registro, los valida y delega el proceso al servicio de autenticación.
     */
   @PostMapping("/register")
    @Operation(
            summary = "Registrar un nuevo usuario",
            description = "Crea una cuenta de usuario a partir de los datos de registro y devuelve un token JWT."
    )
    @ApiResponse(responseCode = "200", description = "Usuario registrado correctamente")
    @ApiResponse(responseCode = "400", description = "Datos de registro inválidos")
    public AuthResponseDTO register(@Valid @RequestBody RegisterRequestDTO request) {

        return authenticationService.register(request);

    }

    /**
     * Endpoint encargado de autenticar un usuario existente.
     * Recibe las credenciales de acceso, las valida y genera un token JWT para el usuario autenticado.
     */
   @PostMapping("/login")
    @Operation(
            summary = "Iniciar sesión",
            description = "Autentica al usuario con sus credenciales y devuelve un token JWT para usar en el header Authorization."
    )
    @ApiResponse(responseCode = "200", description = "Login exitoso, token generado")
    @ApiResponse(responseCode = "401", description = "Credenciales inválidas")
    public AuthResponseDTO login(@Valid @RequestBody LoginRequestDTO request) {

        return authenticationService.login(request);

    }

}